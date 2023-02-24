package com.quarkus.bootcamp.nttdata.domain.services;

import com.quarkus.bootcamp.nttdata.domain.entity.Cards;
import com.quarkus.bootcamp.nttdata.domain.entity.Users;
import com.quarkus.bootcamp.nttdata.domain.respository.CardsRepository;
import com.quarkus.bootcamp.nttdata.domain.respository.UsersRepository;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.card.CardD;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.customer.NaturalPersonD;
import com.quarkus.bootcamp.nttdata.infraestructure.request.UserRequest;
import com.quarkus.bootcamp.nttdata.infraestructure.resource.ICardApi;
import com.quarkus.bootcamp.nttdata.infraestructure.resource.INaturalPersonApi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class UsersService {

  @Inject
  UsersRepository usersRepository;

  @Inject
  CardsRepository cardsRepository;

  @RestClient
  INaturalPersonApi naturalPersonApi;

  @RestClient
  ICardApi iCardApi;

  public Uni<List<Users>> getAll() {
    return usersRepository.listAll();
  }

  public Uni<Users> getById(String id) {
    return usersRepository.findById(new ObjectId(id));
  }

  /**
   * @Inject ReactiveMongoClient mongoClient; public Uni<Void> add(Users users){
   *         Document document = new Document() .append("customerId",
   *         users.getCustomerId()) .append("password", users.getPassword());
   *         return getCollection().insertOne(document)
   *         .onItem().ignore().andContinueWithNull(); } private
   *         ReactiveMongoCollection<Document> getCollection() { return
   *         mongoClient.getDatabase("db_users").getCollection("users");
   */

  public Uni<Cards> add(UserRequest users) {
    Uni<NaturalPersonD> naturalPerson = naturalPersonApi.getById(Long.parseLong(users.getCustomerId()));
    return naturalPerson.flatMap(np -> {
      if (np == null || np.getId() == null) {
        throw new NotFoundException("customer not found");
      }
      Uni<CardD> card = callCardsCustomer(users);
      return card.flatMap(c -> {
        if (c == null || c.getId() == null) {
          throw new NotFoundException("card not found");
        }
        Uni<Users> gottenUser = findByCustomerId(users.getCustomerId());
        return gottenUser.flatMap(user -> {
          if (user != null) {
            throw new NotFoundException();
          }
          return saveUserAccount(users);
        });
      });
    });
  }

  public Uni<CardD> callCardsCustomer(UserRequest usersa) {
    Uni<List<CardD>> usersCards = iCardApi.getAll(Long.parseLong(usersa.getCustomerId()), Long.parseLong("2"));
    return usersCards.onItem().<CardD>disjoint()
          .filter(uc -> (uc.getSerial().equals(usersa.getCard().getSerial())
                && uc.getMonth().intValue() == usersa.getCard().getMonth().intValue()
                && uc.getYear().intValue() == usersa.getCard().getYear().intValue()
                && uc.getCvv() == usersa.getCard().getCvv()))
          .collect().first();

  }

  public Uni<Cards> saveUserAccount(UserRequest users) {
    Users userPersist = new Users();
    userPersist.setCustomerId(users.getCustomerId());
    userPersist.setPassword(users.getPassword());
    return usersRepository.persist(userPersist).flatMap(up -> {
      Cards cards = new Cards();
      cards.setUserId(up.getId());
      cards.setSerial(users.getCard().getSerial());
      return cardsRepository.persist(cards);
    });
  }

  public Uni<Users> findByCustomerId(String customerId) {
    return usersRepository.find("{'customerId': ?1}", customerId).firstResult();
  }

  public Uni<Cards> findCardsBySerial(String serial) {
    return cardsRepository.find("{'serial': ?1}", serial).firstResult();
  }

  public Uni<Users> login(UserRequest users) {
    Uni<Cards> card = findCardsBySerial(users.getCard().getSerial());
    return card.onItem().transformToUni(c -> {
      if (c == null || c.getId() == null) {
        throw new NotFoundException("cards not found");
      }
      return usersRepository.findById(c.getUserId());
    });
  }
}
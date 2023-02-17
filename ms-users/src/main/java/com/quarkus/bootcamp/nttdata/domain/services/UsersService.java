package com.quarkus.bootcamp.nttdata.domain.services;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.quarkus.bootcamp.nttdata.domain.entity.Users;
import com.quarkus.bootcamp.nttdata.domain.respository.UsersRepository;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.customer.NaturalPersonD;
import com.quarkus.bootcamp.nttdata.infraestructure.resource.INaturalPersonApi;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UsersService {

	@Inject
	UsersRepository usersRepository;

	@RestClient
	INaturalPersonApi naturalPersonApi;

	/**
	 * @Inject ReactiveMongoClient mongoClient; public Uni<Void> add(Users users){
	 *         Document document = new Document() .append("customerId",
	 *         users.getCustomerId()) .append("password", users.getPassword());
	 *         return getCollection().insertOne(document)
	 *         .onItem().ignore().andContinueWithNull(); } private
	 *         ReactiveMongoCollection<Document> getCollection() { return
	 *         mongoClient.getDatabase("db_users").getCollection("users");
	 */

	public Uni<Users> add(Users users) {
		NaturalPersonD naturalPerson = naturalPersonApi.getById(Long.parseLong(users.getCustomerId()));
		return Uni.createFrom().item(naturalPerson).flatMap(np -> {
			if (naturalPerson.getId() == null)
				throw new NotFoundException("customer not found");
			Uni<Users> gottenUser = findByCustomerId(users.getCustomerId());
			return gottenUser.flatMap(user -> {
				if (user != null) {
					// agregar nueva excepcion
					throw new NotFoundException();
				} else {
					return usersRepository.persist(users);
				}
			});
		});

	}

	public Uni<Users> findByCustomerId(String customerId) {
		return usersRepository.find("{'customerId': ?1}", customerId).firstResult();
	}
}

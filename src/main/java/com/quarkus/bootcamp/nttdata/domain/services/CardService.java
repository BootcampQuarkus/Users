package com.quarkus.bootcamp.nttdata.domain.services;

import com.quarkus.bootcamp.nttdata.domain.entity.Cards;
import com.quarkus.bootcamp.nttdata.domain.respository.CardsRepository;
import com.quarkus.bootcamp.nttdata.infraestructure.resource.ICardApi;
import com.quarkus.bootcamp.nttdata.infraestructure.resource.INaturalPersonApi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class CardService {

  @Inject
  CardsRepository repository;

  @RestClient
  INaturalPersonApi naturalPersonApi;

  @RestClient
  ICardApi iCardApi;

  public Uni<List<Cards>> getAll() {
    return repository.listAll();
  }

  public Uni<Cards> getById(String id) {
    return repository.findById(new ObjectId(id));
  }

}
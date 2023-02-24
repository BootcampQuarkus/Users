package com.quarkus.bootcamp.nttdata.application;

import com.quarkus.bootcamp.nttdata.domain.entity.Cards;
import com.quarkus.bootcamp.nttdata.domain.services.CardService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/cards")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CardResource {
  @Inject
  CardService service;

  @GET
  public Uni<List<Cards>> getAll() {
    return service.getAll();
  }

  @GET
  @Path("/{id}")
  public Uni<Cards> getById(@PathParam("id") String id) {
    return service.getById(id);
  }
}

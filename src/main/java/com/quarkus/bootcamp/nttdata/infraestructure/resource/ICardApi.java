package com.quarkus.bootcamp.nttdata.infraestructure.resource;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.quarkus.bootcamp.nttdata.infraestructure.entity.card.CardD;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@RegisterRestClient
@Path("/cards")
public interface ICardApi {
	
	@GET
	Uni<List<CardD>> getAll(@QueryParam("customerId") Long customerId, @QueryParam("cardTypeId") Long cardTypeId);
	
}

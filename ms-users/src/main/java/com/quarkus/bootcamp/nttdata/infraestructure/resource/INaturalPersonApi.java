package com.quarkus.bootcamp.nttdata.infraestructure.resource;

import com.quarkus.bootcamp.nttdata.infraestructure.entity.customer.NaturalPersonD;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient
@Path("/naturalperson")
public interface INaturalPersonApi {
  @GET
  @Path("/")
  List<NaturalPersonD> getAll();

  @GET
  @Path("/{id}")
  @Fallback(fallbackMethod = "fallbackGetById")
  NaturalPersonD getById(@PathParam("id") Long id);

  default NaturalPersonD fallbackGetById(Long id) {
    return new NaturalPersonD();
  }
}

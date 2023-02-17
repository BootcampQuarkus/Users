package com.quarkus.bootcamp.nttdata.infraestructure.resource;

import com.quarkus.bootcamp.nttdata.infraestructure.entity.customer.BodyCorporateD;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient
@Path("/bodycorporate")
public interface IBodyCorporateApi {
  @GET
  @Path("/")
  List<BodyCorporateD> getAll();

  @GET
  @Path("/{id}")
  @Fallback(fallbackMethod = "fallbackGetById")
  BodyCorporateD getById(@PathParam("id") Long id);

  default BodyCorporateD fallbackGetById(Long id) {
    return new BodyCorporateD();
  }
}

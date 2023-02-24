package com.quarkus.bootcamp.nttdata.application;


import com.quarkus.bootcamp.nttdata.domain.entity.Users;
import com.quarkus.bootcamp.nttdata.domain.services.UsersService;
import com.quarkus.bootcamp.nttdata.infraestructure.request.ResponseDto;
import com.quarkus.bootcamp.nttdata.infraestructure.request.UserRequest;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsersResource {

  @Inject
  UsersService usersService;

  @POST
  public Uni<Response> add(UserRequest users) {
    return usersService.add(users)
          .onItem().transform(uri ->
                Response.ok(new ResponseDto<>(200, "Se registro correctamente", uri)).status(200).build());
  }

  @Path("/login")
  @POST
  public Uni<Response> login(UserRequest users) {
    return usersService.login(users).onItem().transform(us -> {
      if (us.getPassword().equals(users.getPassword())) {
        return Response.ok(new ResponseDto<>(200, "login successed")).status(200).build();
      }
      return Response.ok(new ResponseDto<>(422, "login fault")).status(422).build();
    });
  }

  @GET
  public Uni<List<Users>> getAll() {
    return usersService.getAll();
  }

  @GET
  @Path("/{id}")
  public Uni<Users> getById(@PathParam("id") String id) {
    return usersService.getById(id);
  }
}

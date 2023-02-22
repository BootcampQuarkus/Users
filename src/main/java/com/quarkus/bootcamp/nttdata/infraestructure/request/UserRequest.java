package com.quarkus.bootcamp.nttdata.infraestructure.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.card.CardD;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
public class UserRequest {
  private String customerId;
  private String password;
  private CardD card;
}

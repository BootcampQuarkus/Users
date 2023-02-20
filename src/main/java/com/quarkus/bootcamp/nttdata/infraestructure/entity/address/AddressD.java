package com.quarkus.bootcamp.nttdata.infraestructure.entity.address;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressD {
  protected Long id;
  protected String address;
  protected Long stateId;
  protected Long cityId;
  protected StateD state;
  protected CityD city;
}

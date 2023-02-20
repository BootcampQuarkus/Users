package com.quarkus.bootcamp.nttdata.infraestructure.entity.customer;

import com.quarkus.bootcamp.nttdata.infraestructure.entity.address.AddressD;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.document.DocumentD;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NaturalPersonD {
  protected Long id;
  protected String name;
  protected String lastName;
  protected Long addressId;
  protected Long documentId;
  protected DocumentD document;
  protected AddressD address;
}

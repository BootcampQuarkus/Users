package com.quarkus.bootcamp.nttdata.infraestructure.entity.customer;

import com.quarkus.bootcamp.nttdata.infraestructure.entity.address.AddressD;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.document.DocumentD;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BodyCorporateD {
  protected Long id;
  protected Long documentId;
  protected Long addressId;
  protected String name;
  protected DocumentD document;
  protected AddressD address;
}

package com.quarkus.bootcamp.nttdata.domain.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
@MongoEntity(collection = "cards")
public class Cards {

  private ObjectId id;
  private ObjectId userId;
  private String serial;

}

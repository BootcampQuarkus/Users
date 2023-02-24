package com.quarkus.bootcamp.nttdata.domain.entity;


import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
@MongoEntity(collection = "users")
public class Users {

  //@BsonId
  private ObjectId id;
  private Long customerId;
  private String password;
}

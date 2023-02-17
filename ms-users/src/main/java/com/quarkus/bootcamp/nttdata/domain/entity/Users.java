package com.quarkus.bootcamp.nttdata.domain.entity;


import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;

@Data
@MongoEntity(collection = "users")
public class Users  {
	
	//@BsonId
     private ObjectId  id;
     private String customerId;
     private String password;
}

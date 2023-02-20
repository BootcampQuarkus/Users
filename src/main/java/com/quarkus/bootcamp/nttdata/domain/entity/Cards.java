package com.quarkus.bootcamp.nttdata.domain.entity;

import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;

@Data
@MongoEntity(collection = "cards")
public class Cards {
	
    private ObjectId  id;
    private ObjectId userId;
    private String serial;

}

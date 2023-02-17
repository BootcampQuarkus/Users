package com.quarkus.bootcamp.nttdata.domain.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Data;

@Data
@MongoEntity(collection = "cards")
public class Cards {
	
    private String  id;
    private String userId;
    private String serial;

}

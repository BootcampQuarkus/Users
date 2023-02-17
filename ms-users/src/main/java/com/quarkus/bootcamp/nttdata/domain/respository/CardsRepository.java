package com.quarkus.bootcamp.nttdata.domain.respository;

import com.quarkus.bootcamp.nttdata.domain.entity.Cards;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;

public class CardsRepository implements ReactivePanacheMongoRepository<Cards> {
	
}

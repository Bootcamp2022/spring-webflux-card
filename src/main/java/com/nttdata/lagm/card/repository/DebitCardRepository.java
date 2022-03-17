package com.nttdata.lagm.card.repository;

import com.nttdata.lagm.card.model.DebitCard;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebitCardRepository extends ReactiveMongoRepository<DebitCard, String> {
}

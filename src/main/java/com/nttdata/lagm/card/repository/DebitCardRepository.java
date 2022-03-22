package com.nttdata.lagm.card.repository;

import com.nttdata.lagm.card.model.DebitCard;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface DebitCardRepository extends ReactiveMongoRepository<DebitCard, String> {
    Mono<DebitCard> findByCardNumber(String cardNumber);
}

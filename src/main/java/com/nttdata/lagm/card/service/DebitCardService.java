package com.nttdata.lagm.card.service;

import com.nttdata.lagm.card.dto.DebitCardRequestDto;
import com.nttdata.lagm.card.model.DebitCard;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DebitCardService {
    Mono<DebitCard> create(DebitCardRequestDto debitCardRequestDto);
    Flux<DebitCard> findAll();
    Mono<DebitCard> findById(String id);
    Mono<DebitCard> update(DebitCardRequestDto debitCardRequestDto);
    Mono<DebitCard> delete(String id);
}

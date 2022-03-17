package com.nttdata.lagm.card.service;

import com.nttdata.lagm.card.dto.DebitCardRequestDto;
import com.nttdata.lagm.card.model.DebitCard;
import com.nttdata.lagm.card.repository.DebitCardRepository;
import com.nttdata.lagm.card.util.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DebitCardServiceImpl implements DebitCardService {

    private Logger LOGGER = LoggerFactory.getLogger(DebitCardServiceImpl.class);

    @Autowired
    private DebitCardRepository debitCardRepository;

    @Override
    public Mono<DebitCard> create(DebitCardRequestDto debitCardRequestDto) {
        DebitCard debitCard = Converter.convertToDebitCard(debitCardRequestDto);
        LOGGER.info("Creating debitCard: {}", debitCard);
        return debitCardRepository.save(debitCard);
    }

    @Override
    public Flux<DebitCard> findAll() {
        return debitCardRepository.findAll();
    }

    @Override
    public Mono<DebitCard> findById(String id) {
        return debitCardRepository.findById(id);
    }

    @Override
    public Mono<DebitCard> update(DebitCardRequestDto debitCardRequestDto) {
        DebitCard debitCard = Converter.convertToDebitCard(debitCardRequestDto);
        LOGGER.info("Creating debitCard: {}", debitCard);
        return debitCardRepository.save(debitCard);
    }

    @Override
    public Mono<DebitCard> delete(String id) {
        return debitCardRepository.findById(id).flatMap(debitCard -> {
            debitCard.setStatus(false);
            return debitCardRepository.save(debitCard);
        });
    }
}

package com.nttdata.lagm.card.controller;

import com.nttdata.lagm.card.dto.DebitCardRequestDto;
import com.nttdata.lagm.card.model.DebitCard;
import com.nttdata.lagm.card.service.DebitCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/debitCard")
public class DebitCardController {

    private Logger LOGGER = LoggerFactory.getLogger(DebitCardController.class);

    @Autowired
    private DebitCardService debitCardService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Mono<DebitCard> create(@Valid @RequestBody DebitCardRequestDto debitCardRequestDto) {
        return debitCardService.create(debitCardRequestDto);
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    private Flux<DebitCard> findAll() {
        return debitCardService.findAll();
    }

    @GetMapping(value="/{id}")
    @ResponseStatus(HttpStatus.OK)
    private Mono<DebitCard> findById(@PathVariable("id") String id) {
        return debitCardService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private Mono<DebitCard> delete(@PathVariable("id") String id) {
        return debitCardService.delete(id);
    }

    @GetMapping(value="/cardNumber/{cardNumber}")
    @ResponseStatus(HttpStatus.OK)
    private Mono<DebitCard> findByCardNumber(@PathVariable("cardNumber") String cardNumber) {
        return debitCardService.findByCardNumber(cardNumber);
    }
}

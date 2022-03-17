package com.nttdata.lagm.card.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.lagm.card.dto.DebitCardRequestDto;
import com.nttdata.lagm.card.model.DebitCard;
import com.nttdata.lagm.card.service.DebitCardService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
}

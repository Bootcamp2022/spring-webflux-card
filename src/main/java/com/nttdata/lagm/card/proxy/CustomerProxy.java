package com.nttdata.lagm.card.proxy;

import com.nttdata.lagm.card.model.customer.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerProxy {
	Flux<Customer> findAll();
	Mono<Customer> findById(String id);
	Mono<Customer> findByDni(String id);
}

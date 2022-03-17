package com.nttdata.lagm.card.proxy;

import com.nttdata.lagm.card.model.account.BankAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountProxy {
	public Flux<BankAccount> findAll();
	public Mono<BankAccount> findById(String id);
	public Mono<BankAccount> findByAccountNumber(String accountNumber);
	public Mono<BankAccount> update(BankAccount bankAccount);
	public Flux<BankAccount> findAllByDni(String dni);
	public Flux<BankAccount> findAllByAccountNumberAndDni(String accountNumber, String dni);
}

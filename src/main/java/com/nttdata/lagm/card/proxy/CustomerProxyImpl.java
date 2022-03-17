package com.nttdata.lagm.card.proxy;

import com.nttdata.lagm.card.model.customer.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomerProxyImpl implements CustomerProxy {

	private Logger LOGGER = LoggerFactory.getLogger(CustomerProxyImpl.class);

	@Value("${config-eureka.base.customer.endpoint}")
	private String endpointCustomer;
	
	@Autowired
	@Qualifier("wcLoadBalanced")
	private WebClient.Builder webClientBuilder;

	@Override
	public Flux<Customer> findAll() {
		return webClientBuilder
				//.clientConnector(RestUtils.getDefaultClientConnector())
				.build()
				.get()
				.uri(endpointCustomer)
				.retrieve()
				.bodyToFlux(Customer.class);
	}
	
	@Override
	public Mono<Customer> findById(String id) {
		Map<String,Object> params = new HashMap<>();
		params.put("id", id);
		return webClientBuilder
				// .clientConnector(RestUtils.getDefaultClientConnector())
				.build()
				.get()
				.uri(endpointCustomer + "/{id}", params)
				.retrieve()
				.bodyToMono(Customer.class);
	}

	@Override
	public Mono<Customer> findByDni(String dni) {
		LOGGER.info("{} - findByDni {}", "CustomerProxyImpl", dni);
		Map<String,Object> params = new HashMap<>();
		params.put("dni", dni);
		return webClientBuilder
				// .clientConnector(RestUtils.getDefaultClientConnector())
				.build()
				.get()
				.uri(endpointCustomer + "/dni/{dni}", params)
				.retrieve()
				.bodyToMono(Customer.class);
	}

}

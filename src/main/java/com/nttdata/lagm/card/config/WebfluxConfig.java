package com.nttdata.lagm.card.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@EnableWebFlux
@Configuration
public interface WebfluxConfig extends WebFluxConfigurer {
}

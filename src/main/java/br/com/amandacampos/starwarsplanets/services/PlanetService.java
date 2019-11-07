package br.com.amandacampos.starwarsplanets.services;

import br.com.amandacampos.starwarsplanets.models.Planet;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface PlanetService {
    Mono<Planet> save(Planet planet);
    Mono<Planet> findById(Long id);
    Mono<List<Planet>> findByName(String name);
    Flux<Planet> findAll();
//    ResponseEntity<?> delete(Long id);
}

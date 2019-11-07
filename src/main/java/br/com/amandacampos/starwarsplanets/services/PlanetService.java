package br.com.amandacampos.starwarsplanets.services;

import br.com.amandacampos.starwarsplanets.models.Planet;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface PlanetService {
    Mono<Planet> save(Planet planet);
//    ResponseEntity<Planet> findById(Long id);
//    Planet findByName(String name);
    Flux<Planet> findAll();
//    ResponseEntity<?> delete(Long id);
}

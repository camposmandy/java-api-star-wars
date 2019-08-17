package br.com.amandacampos.starwarsplanets.services;

import br.com.amandacampos.starwarsplanets.models.Planet;
import org.springframework.http.ResponseEntity;

public interface PlanetService {
    ResponseEntity<Planet> save(Planet planet);
    ResponseEntity<Planet> findById(Long id);
    Planet findByName(String name);
    ResponseEntity<?> delete(Long id);
}

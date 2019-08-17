package br.com.amandacampos.starwarsplanets.services;

import br.com.amandacampos.starwarsplanets.models.Planet;

public interface PlanetService {
    Planet save(Planet planet);
    Planet findById(Long id);
    Planet findByName(String name);
    void delete(Long id);
}

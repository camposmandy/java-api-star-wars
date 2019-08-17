package br.com.amandacampos.starwarsplanets.services;

import br.com.amandacampos.starwarsplanets.models.Planet;

public interface PlanetService {
    Planet save(Planet planet);
    void findById(Long id);
    void findByName(Planet planet);
    void delete(Planet planet);
}

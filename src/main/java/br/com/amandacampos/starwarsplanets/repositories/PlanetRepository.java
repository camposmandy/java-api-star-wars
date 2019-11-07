package br.com.amandacampos.starwarsplanets.repositories;

import br.com.amandacampos.starwarsplanets.models.Planet;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetRepository extends ReactiveMongoRepository<Planet, Long> {
}

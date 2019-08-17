package br.com.amandacampos.starwarsplanets.repositories;

import br.com.amandacampos.starwarsplanets.models.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long> {
}

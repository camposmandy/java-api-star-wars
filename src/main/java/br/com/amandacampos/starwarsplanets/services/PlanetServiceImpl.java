package br.com.amandacampos.starwarsplanets.services;

import br.com.amandacampos.starwarsplanets.connectors.BaseConnector;
import br.com.amandacampos.starwarsplanets.models.Planet;
import br.com.amandacampos.starwarsplanets.models.PlanetBase;
import br.com.amandacampos.starwarsplanets.repositories.PlanetRepository;
import br.com.amandacampos.starwarsplanets.models.enumarator.PlanetExceptionEnum;
import br.com.amandacampos.starwarsplanets.services.exception.PlanetNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanetServiceImpl implements PlanetService {

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private BaseConnector connector;

    /**
     * Add planet to database, if found in Star Wars API,
     * and complements your data (movie appearances).
     * @param planet Planet
     * @return Planet
     * @throws PlanetNotFoundException
     */
    @Override
    public ResponseEntity<Planet> save(Planet planet) throws PlanetNotFoundException {
        PlanetBase planetResult = connector.searchPlanet(planet.getName());

        if (planetResult.getResults().size() == 0) {
            throw new PlanetNotFoundException(PlanetExceptionEnum.OBJ_NOT_EXIST.getStatus());
        }

        planet.setMovieAppearances(countFilms(planetResult.getResults().get(0).getFilms()));
        planetRepository.save(planet);

        return ResponseEntity.status(HttpStatus.CREATED).body(planet);
    }

    /**
     * Searches for a planet by its identifier in the database.
     * @param id Long
     * @return Planet
     * @throws PlanetNotFoundException
     */
    @Override
    public ResponseEntity<Planet> findById(Long id) throws PlanetNotFoundException {
        return planetRepository
                .findById(id)
                .map(planet -> ResponseEntity.ok().body(planet))
                .orElseThrow(() -> new PlanetNotFoundException(PlanetExceptionEnum.OBJ_NOT_FOUND.getStatus()));
    }

    /**
     * Searches for a planet by its name in the database.
     * @param name String
     * @return Planet[]
     * @throws PlanetNotFoundException
     */
    @Override
    public Planet findByName(String name) throws PlanetNotFoundException {
        List<Planet> planets = planetRepository.findAll();
        Optional<Planet> planet = planets.stream().filter(p -> name.equals(p.getName())).findFirst();

        return planet.orElseThrow(() -> new PlanetNotFoundException(PlanetExceptionEnum.OBJ_NOT_FOUND.getStatus()));
    }

    @Override
    public List<Planet> findAll() {
        List<Planet> planets = planetRepository.findAll();
        return planets;
    }

    /**
     * Searches for a planet by its identifier and excludes it from database.
     * @param id Long
     * @return ResponseEntity
     * @throws PlanetNotFoundException
     */
    @Override
    public ResponseEntity<?> delete(Long id) throws PlanetNotFoundException {
        return planetRepository.findById(id)
                .map(planet -> {
                        planetRepository.delete(planet);
                        return ResponseEntity.noContent().build();
                        })
                .orElseThrow(() -> new PlanetNotFoundException(PlanetExceptionEnum.OBJ_NOT_FOUND.getStatus()));
    }

    /**
     * Check number of appearances.
     *
     * @param films String[]
     * @return Integer
     */
    private Integer countFilms(List<String> films) {
        return films.size();
    }
}

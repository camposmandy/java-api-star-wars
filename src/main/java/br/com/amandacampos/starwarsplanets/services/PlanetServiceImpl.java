package br.com.amandacampos.starwarsplanets.services;

import br.com.amandacampos.starwarsplanets.connectors.ReactiveServerConnector;
import br.com.amandacampos.starwarsplanets.models.Planet;
import br.com.amandacampos.starwarsplanets.models.PlanetBase;
import br.com.amandacampos.starwarsplanets.models.enumarator.PlanetExceptionEnum;
import br.com.amandacampos.starwarsplanets.repositories.PlanetRepository;
import br.com.amandacampos.starwarsplanets.services.exception.PlanetNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.util.List;

@Service
public class PlanetServiceImpl implements PlanetService {
    private static final String URL_API_STAR_WARS = "https://swapi.co/api/";

    @Autowired
    private PlanetRepository planetRepository;

    private ReactiveServerConnector reactiveServerConnector;

    @Autowired
    public PlanetServiceImpl(ReactiveServerConnector reactiveServerConnector) {
        this.reactiveServerConnector = reactiveServerConnector;
    }

    /**
     * Add planet to database, if found in Star Wars API,
     * and complements your data (movie appearances).
     * @param planet Planet
     * @return Planet
     * @throws PlanetNotFoundException Planet name invalid
     */
    @Override
    public Mono<Planet> save(Planet planet) throws PlanetNotFoundException {
        String url = URL_API_STAR_WARS + "planets/?search=" + planet.getName();
        return reactiveServerConnector.doGet(url)
                .flatMap(clientResponse -> {
                    Mono<PlanetBase> planetResult = clientResponse.bodyToMono(PlanetBase.class);
                    return planetResult.flatMap(response -> {
                        if (response.getResults().size() == 0) {
                            throw new PlanetNotFoundException(PlanetExceptionEnum.OBJ_NOT_EXIST.getStatus());
                        }
                        planet.setMovieAppearances(countFilms(response.getResults().get(0).getFilms()));
                        planet.setId(this.getCurrentId());
                        return planetRepository.save(planet);
                    });
                });
    }

    /**
     * Searches for a planet by its identifier in the database.
     * @param id Long
     * @return Planet
     * @throws PlanetNotFoundException Planet not found
     */
    @Override
    public Mono<Planet> findById(Long id) throws PlanetNotFoundException {
        return planetRepository
                .findById(id)
                .switchIfEmpty(Mono.error(new PlanetNotFoundException(PlanetExceptionEnum.OBJ_NOT_FOUND.getStatus())));
    }

    /**
     * Searches for a planet by its name in the database.
     * @param name String
     * @return Planet[]
     */
    @Override
    public Mono<List<Planet>> findByName(String name) throws PlanetNotFoundException {
        Flux<Planet> planets = this.findAll();
        return planets.filter(p -> name.equals(p.getName())).collectList();
    }

    @Override
    public Flux<Planet> findAll() {
        return planetRepository.findAll();
    }

    /**
     * Searches for a planet by its identifier and excludes it from database.
     * @param id Long
     * @return ResponseEntity
     * @throws PlanetNotFoundException
     */
    @Override
    public Mono<?> delete(Long id) throws PlanetNotFoundException {
        return this.findById(id)
                .flatMap(planet -> planetRepository
                            .delete(planet));
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


    /**
     * Generate random id
     * @return Long
     */
    private Long getCurrentId(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.getTime();
    }
}

package br.com.amandacampos.starwarsplanets.services;

import br.com.amandacampos.starwarsplanets.connectors.ReactiveServerConnector;
import br.com.amandacampos.starwarsplanets.models.Planet;
import br.com.amandacampos.starwarsplanets.services.exception.PlanetNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class PlanetServiceImpl implements PlanetService {
    private static final String URL_API_STAR_WARS = "https://swapi.co/api/";

//    @Autowired
//    private PlanetRepository planetRepository;

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
     * @throws PlanetNotFoundException
     */
    @Override
    public Mono<Planet> save(Planet planet) throws PlanetNotFoundException {
//        PlanetBase planetResult = connector.searchPlanet(planet.getName());
        String url = URL_API_STAR_WARS + "planets/?search=" + planet.getName();
        System.out.println("save mono");
        return reactiveServerConnector.doPost(url, planet)
                .flatMap(clientResponse -> {
                    System.out.printf("\n Client Response", clientResponse);

                    Mono<Planet> planetResult = clientResponse.bodyToMono(Planet.class);
                    return planetResult.flatMap(response -> {
                        System.out.printf("\n PLANET RESULT", planetResult);
                        return Mono.just(response);
//                        if (response.getResults().size() == 0) {
//                            throw new PlanetNotFoundException(PlanetExceptionEnum.OBJ_NOT_EXIST.getStatus());
//                        }
//
//                        planet.setMovieAppearances(countFilms(response.getResults().get(0).getFilms()));
//                        return planetRepository.save(planet);
                    });
                });
    }

//    /**
//     * Searches for a planet by its identifier in the database.
//     * @param id Long
//     * @return Planet
//     * @throws PlanetNotFoundException
//     */
//    @Override
//    public ResponseEntity<Planet> findById(Long id) throws PlanetNotFoundException {
////        return planetRepository
////                .findById(id)
////                .map(planet -> ResponseEntity.ok().body(planet))
////                .orElseThrow(() -> new PlanetNotFoundException(PlanetExceptionEnum.OBJ_NOT_FOUND.getStatus()));
//        return null;
//    }
//
//    /**
//     * Searches for a planet by its name in the database.
//     * @param name String
//     * @return Planet[]
//     * @throws PlanetNotFoundException
//     */
//    @Override
//    public Planet findByName(String name) throws PlanetNotFoundException {
////        List<Planet> planets = planetRepository.findAll();
////        Optional<Planet> planet = planets.stream().filter(p -> name.equals(p.getName())).findFirst();
////
////        return planet.orElseThrow(() -> new PlanetNotFoundException(PlanetExceptionEnum.OBJ_NOT_FOUND.getStatus()));
//        return null;
//    }
//
//    @Override
//    public List<Planet> findAll() {
////        List<Planet> planets = planetRepository.findAll();
////        return planets;
//        return null;
//    }
//
//    /**
//     * Searches for a planet by its identifier and excludes it from database.
//     * @param id Long
//     * @return ResponseEntity
//     * @throws PlanetNotFoundException
//     */
//    @Override
//    public ResponseEntity<?> delete(Long id) throws PlanetNotFoundException {
////        return planetRepository.findById(id)
////                .map(planet -> {
////                        planetRepository.delete(planet);
////                        return ResponseEntity.noContent().build();
////                        })
////                .orElseThrow(() -> new PlanetNotFoundException(PlanetExceptionEnum.OBJ_NOT_FOUND.getStatus()));
//        return null;
//    }

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

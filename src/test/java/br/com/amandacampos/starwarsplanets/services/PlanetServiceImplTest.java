package br.com.amandacampos.starwarsplanets.services;

import br.com.amandacampos.starwarsplanets.models.Planet;
import br.com.amandacampos.starwarsplanets.repositories.PlanetRepository;
import br.com.amandacampos.starwarsplanets.util.Util;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class PlanetServiceImplTest {

    @Mock
    PlanetRepository repository;

    @InjectMocks
    PlanetServiceImpl planetService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Test: Find Planet by name. <Expected> Return a list.")
    public void findByName() {
        Planet planet = getMockPlanet();

        Mockito.when(repository.findAll())
                .thenReturn(Flux.just(planet));

        Mono<List<Planet>> response = planetService.findByName(planet.getName());

        response.subscribe(resp -> {
            Assertions.assertEquals(ArrayList.class, resp.getClass());
            Assertions.assertEquals(1, resp.size());
        });
    }

    @Test
    @DisplayName("Test: Find Planet by name. <Expected> Return a empty list.")
    public void findByNameEmpty() {
        Planet planet = getMockPlanet();

        Mockito.when(repository.findAll())
                .thenReturn(Flux.just(planet));

        Mono<List<Planet>> response = planetService.findByName("Endor");

        response.subscribe(resp -> {
            Assertions.assertEquals(ArrayList.class, resp.getClass());
            Assertions.assertFalse(resp.size() >= 1);
        });
    }

    private Planet getMockPlanet() {
        Planet planet = new Planet();

        planet.setClimate("temperate");
        planet.setName("Alderaan");
        planet.setTerrain("grasslands, mountains");
        planet.setId(Util.getRandomLong());
        planet.setMovieAppearances(12);

        return planet;
    }
}
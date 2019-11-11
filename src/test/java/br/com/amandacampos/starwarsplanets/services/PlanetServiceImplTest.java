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

    @Test
    @DisplayName("Test: Find Planet by id. <Expected> Return a Planet.")
    public void findById() {
        Planet planet = getMockPlanet();

        Mockito.when(repository.findById(planet.getId()))
                .thenReturn(Mono.just(planet));

        Mono<Planet> response = planetService.findById(planet.getId());

        response.subscribe(resp -> {
            Assertions.assertEquals(Planet.class, resp.getClass());
            Assertions.assertEquals(resp.getId(), planet.getId());
        });
    }

    @Test(expected = NullPointerException.class)
    @DisplayName("Test: Find Planet by id. <Expected> Not found a Planet.")
    public void findByIdNotFound() {
        Planet planet = getMockPlanet();
        planet.setId(Long.parseLong("1"));

        Mockito.when(repository.findById(planet.getId()))
                .thenReturn(Mono.just(planet));

        Mono<Planet> response = planetService.findById(Long.parseLong("3"));
    }

    @Test
    @DisplayName("Test: Delete Planet by id. <Expected> Delete a Planet.")
    public void delete() {
        Planet planet = getMockPlanet();

        Mockito.when(repository.findById(planet.getId()))
                .thenReturn(Mono.just(planet));

        Mockito.when(repository.delete(planet))
                .thenReturn(Mono.empty());

       Mono<?> response = planetService.delete(planet.getId());
    }

    @Test(expected = NullPointerException.class)
    @DisplayName("Test: Delete Planet by id. <Expected> Not found a Planet.")
    public void deleteNotFound() {
        Planet planet = getMockPlanet();
        planet.setId(Long.parseLong("1"));

        Mockito.when(repository.findById(Long.parseLong("5")))
                .thenReturn(Mono.just(planet));

        Mockito.when(repository.delete(planet))
                .thenReturn(Mono.empty());

        Mono<?> response = planetService.delete(planet.getId());
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
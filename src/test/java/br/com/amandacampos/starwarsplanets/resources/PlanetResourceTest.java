package br.com.amandacampos.starwarsplanets.resources;


import br.com.amandacampos.starwarsplanets.util.Util;
import br.com.amandacampos.starwarsplanets.models.Planet;
import br.com.amandacampos.starwarsplanets.repositories.PlanetRepository;
import br.com.amandacampos.starwarsplanets.services.PlanetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
@WebFluxTest(PlanetResource.class)
public class PlanetResourceTest {

    @Mock
    PlanetRepository repository;

    @MockBean
    PlanetService planetService;

    @Autowired
    private WebTestClient webClient;

    @Test
    public void testInsert() {
        Planet planet = getMockPlanet();
        Mockito
                .when(repository.save(planet))
                .thenReturn(Mono.just(planet));

        webClient.post()
                .uri("/planets")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(planet))
                .exchange()
                .expectStatus().isCreated();

        Mockito.verify(planetService, times(1)).save(planet);
    }

    @Test
    public void testListPlanets() {
        Mockito
                .when(repository.findAll())
                .thenReturn(Flux.just(getMockPlanet()));

        webClient.get()
                .uri("/planets/list")
                .exchange()
                .expectStatus().isOk();

        Mockito.verify(planetService, times(1)).findAll();
    }

    @Test
    public void testFindPlanetByName() {
        Planet planet = getMockPlanet();

        Mockito.when(repository.findAll())
                .thenReturn(Flux.just(planet));

        webClient.get()
                .uri("/planets?name=" + planet.getName())
                .exchange()
                .expectStatus().isOk();

        Mockito.verify(planetService, times(1)).findByName("Alderaan");
    }

    @Test
    public void testFindPlanetById() {
        Planet planet = getMockPlanet();

        Mockito.when(repository.findById(planet.getId()))
                .thenReturn(Mono.just(planet));

        webClient.get()
                .uri("/planets/" + planet.getId())
                .exchange()
                .expectStatus().isOk();

        Mockito.verify(planetService, times(1)).findById(planet.getId());
    }

    @Test
    public void testDeletePlanet() {
        Planet planet = getMockPlanet();

        Mockito.when(repository.findById(planet.getId()))
                .thenReturn(Mono.empty());

        webClient.delete()
                .uri("/planets/" + planet.getId())
                .exchange()
                .expectStatus().isOk();

        Mockito.verify(planetService, times(1)).delete(planet.getId());
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

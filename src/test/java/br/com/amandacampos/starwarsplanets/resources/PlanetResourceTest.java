package br.com.amandacampos.starwarsplanets.resources;


import br.com.amandacampos.starwarsplanets.models.Planet;
import br.com.amandacampos.starwarsplanets.repositories.PlanetRepository;
import br.com.amandacampos.starwarsplanets.services.PlanetService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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

    @Mock
    PlanetResource planetResource;

    @Autowired
    private WebTestClient webClient;

    @Test
    public void testInsert() {
        Planet planet = new Planet();
        planet.setClimate("temperate");
        planet.setName("Alderaan");
        planet.setTerrain("grasslands, mountains");
        planet.setId(getRandomLong());
        planet.setMovieAppearances(12);

        Mockito
                .when(repository.save(planet))
                .thenReturn(Mono.just(planet));

        webClient.post()
                .uri("/planets")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(planet))
                .exchange()
                .expectStatus().isCreated();

        planetResource.insert(planet);

        Mockito.verify(planetService, times(1)).save(planet);
    }

    @Test
    public void testListPlanets() {
        Mockito
                .when(repository.findAll())
                .thenReturn(Flux.just(new Planet()));

        webClient.get()
                .uri("/planets/list")
                .exchange();

        planetResource.listPlanets();

        Mockito.verify(planetService, times(1)).findAll();
    }

    @Test
    public void testFindPlanetByName() {
    }

    @Test
    public void testFindPlanetById() {
    }

    @Test
    public void testDeletePlanet() {
    }

    private Long getRandomLong() {
        long leftLimit = 1L;
        long rightLimit = 10L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }
}

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.times;

@SpringBootTest()
@RunWith(SpringRunner.class)
public class PlanetResourceTest {

    @Mock
    PlanetRepository repository;

    @Mock
    PlanetService planetService;

    @InjectMocks
    PlanetResource planetResource;

    private WebTestClient webClient;

    @Before()
    public void setup() {
        webClient = WebTestClient.bindToServer().baseUrl("http://localhost:8080").build();
    }

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

//    void testListPlanets() {
//    }
//
//    void testFindPlanetByName() {
//    }
//
//    void testFindPlanetById() {
//    }
//
//    void testDeletePlanet() {
//    }

    private Long getRandomLong() {
        long leftLimit = 1L;
        long rightLimit = 10L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }
}

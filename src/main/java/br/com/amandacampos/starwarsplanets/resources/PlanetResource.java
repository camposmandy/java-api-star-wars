package br.com.amandacampos.starwarsplanets.resources;

import br.com.amandacampos.starwarsplanets.models.Planet;
import br.com.amandacampos.starwarsplanets.services.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/planets/v1")
public class PlanetResource {
    // todo: retornar ResponseEntity nos metodos

    @Autowired
    private PlanetService planetService;

    /**
     * Add a new planet to the base.
     */
    @PostMapping(path = "/add")
    public Planet insert(@Valid @RequestBody Planet planet) {
        return planetService.save(planet);
    }

    /**
     * Lists all planets registered in the base.
     */
    public void listPlanets() {}


    /**
     * Search for a planet by name on base.
     */
    public void findPlanetByName() {}


    /**
     * Search for a planet by id on base.
     */
    @GetMapping(path = "/{id}")
    public Planet findPlanetById(@PathVariable Long id) {
        return planetService.findById(id);
    }


    /**
     * Excludes a planet from the base.
     */
    public void deletePlanet() {}

}

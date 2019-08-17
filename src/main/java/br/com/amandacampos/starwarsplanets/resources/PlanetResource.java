package br.com.amandacampos.starwarsplanets.resources;

import br.com.amandacampos.starwarsplanets.models.Planet;
import br.com.amandacampos.starwarsplanets.services.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/planets")
public class PlanetResource {

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
    public void findPlanetById() {}


    /**
     * Excludes a planet from the base.
     */
    public void deletePlanet() {}

}

package br.com.amandacampos.starwarsplanets.resources;

import br.com.amandacampos.starwarsplanets.domain.Planet;
import br.com.amandacampos.starwarsplanets.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/planets")
public class PlanetResource {

    @Autowired
    private PlanetService planetService;

    /**
     * Add a new planet to the base.
     */
    @PostMapping(path = "/add")
    public void addPlanet(@RequestBody Planet planet) {
        planetService.savePlanet(planet);
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

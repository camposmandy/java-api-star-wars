package br.com.amandacampos.starwarsplanets.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/planets")
public class PlanetResource {


    /**
     * Add a new planet to the base.
     */
    public void addPlanet() {}


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

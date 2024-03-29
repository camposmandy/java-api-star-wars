package br.com.amandacampos.starwarsplanets.resources;

import br.com.amandacampos.starwarsplanets.models.Planet;
import br.com.amandacampos.starwarsplanets.services.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Valid;
import java.util.List;

@EnableSwagger2
@RestController
@RequestMapping("/planets")
public class PlanetResource {

    @Autowired
    private PlanetService planetService;

    /**
     * Add a new planet to the database.
     * @param planet Planet
     * @return ResponseEntity<Planet>
     */
    @PostMapping
    public ResponseEntity<Planet> insert(@Valid @RequestBody Planet planet) {
        return planetService.save(planet);
    }

    /**
     * Lists all planets registered in the database.
     */
    @GetMapping(path = "/list")
    public List<Planet> listPlanets() {
        return planetService.findAll();
    }

    /**
     * Search for a planet by name on database.
     * @param name String
     * @return Planet
     */
    @GetMapping()
    public Planet findPlanetByName(@Param("name") String name) {
        return planetService.findByName(name);
    }

    /**
     * Search for a planet by id on database.
     * @param id Long
     * @return Planet
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<Planet> findPlanetById(@PathVariable Long id) {
        return planetService.findById(id);
    }

    /**
     * Excludes a planet from the database.
     * @param id Long
     * @return ResponseEntity
     */
    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity deletePlanet(@PathVariable Long id) {
        return planetService.delete(id);
    }
}

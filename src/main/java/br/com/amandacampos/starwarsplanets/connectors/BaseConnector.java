package br.com.amandacampos.starwarsplanets.connectors;


import br.com.amandacampos.starwarsplanets.models.PlanetBase;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@FeignClient(value = "starwarsapi", url = "https://swapi.co/api/")
public interface BaseConnector {

    /**
     * Search for a planet by name in the Star Wars API.
     *
     * @param name String
     * @return PlanetBase
     */
    @GetMapping(value = "planets/?search={name}")
    PlanetBase searchPlanet(@PathVariable String name);
}

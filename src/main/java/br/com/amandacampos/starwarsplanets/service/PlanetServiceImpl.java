package br.com.amandacampos.starwarsplanets.service;

import br.com.amandacampos.starwarsplanets.domain.Planet;
import org.springframework.stereotype.Component;

@Component
public class PlanetServiceImpl implements PlanetService {

    @Override
    public void savePlanet(Planet planet) {
        System.out.println(planet);
    }
}

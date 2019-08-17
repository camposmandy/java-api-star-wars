package br.com.amandacampos.starwarsplanets.services;

import br.com.amandacampos.starwarsplanets.connectors.BaseConnector;
import br.com.amandacampos.starwarsplanets.models.Planet;
import br.com.amandacampos.starwarsplanets.models.PlanetBase;
import br.com.amandacampos.starwarsplanets.repositories.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanetServiceImpl implements PlanetService {

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private BaseConnector connector;

    @Override
    public Planet save(Planet planet) {
        PlanetBase planetResult = connector.searchPlanet(planet.getName());

        if (planetResult != null) {
            planet.setMovieAppearances(countFilms(planetResult.getResults().get(0).getFilms()));
            planetRepository.save(planet);
            System.out.println(planet);
            return planet;
        }
        // caso não exista
        // retornar msg de erro com motivo
        return null;
    }

    /**
     * Check number of appearances.
     *
     * @param films String[]
     * @return Integer
     */
    private Integer countFilms(List<String> films) {
        return films.size();
    }

    @Override
    public void findById(Long id) {

    }

    @Override
    public void findByName(Planet planet) {

    }

    @Override
    public void delete(Planet planet) {

    }
}

package br.com.amandacampos.starwarsplanets.services;

import br.com.amandacampos.starwarsplanets.connectors.BaseConnector;
import br.com.amandacampos.starwarsplanets.models.Planet;
import br.com.amandacampos.starwarsplanets.models.PlanetBase;
import br.com.amandacampos.starwarsplanets.repositories.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanetServiceImpl implements PlanetService {

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private BaseConnector connector;

    /**
     * Adicionar planeta na base se for encontrado na API e
     * complementa seus dados (aparições em filmes).
     *
     * @param planet Planet
     * @return Planet
     */
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
        // todo: implementar exception
        return null;
    }

    @Override
    public Planet findById(Long id) {
        Optional<Planet> planets = planetRepository.findById(id);
        // todo: implementar exception
        return planets.orElse(null);
    }

    @Override
    public Planet findByName(String name) {
        List<Planet> planets = planetRepository.findAll();
        Optional<Planet> planet = planets.stream().filter(p -> name.equals(p.getName())).findFirst();

        // todo: implementar exception
        return planet.orElse(null);
    }

    @Override
    public void delete(Long id) {
        // todo: implementar exception
        planetRepository.deleteById(id);
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
}

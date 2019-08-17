package br.com.amandacampos.starwarsplanets.models;

import lombok.Data;

import java.util.List;

/**
 * Planet details from Star Wars API
 */
@Data
public class PlanetResult {
    private String name;
    private String rotation_period;
    private String orbital_period;
    private String diameter;
    private String climate;
    private String gravity;
    private String terrain;
    private String surface_water;
    private String population;
    private List<String> residents;
    private List<String> films;
    private String created;
    private String edited;
    private String url;
}

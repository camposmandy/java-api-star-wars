package br.com.amandacampos.starwarsplanets.models;

import lombok.Data;

import java.util.List;

/**
 * Planet details from Star Wars API.
 */
@Data
public class PlanetResult {
    /**
     * The name of this planet.
     */
    private String name;
    /**
     * The number of standard hours it takes for this planet to complete a single rotation on its axis.
     */
    private String rotation_period;
    /**
     * The number of standard days it takes for this planet to complete a single orbit of its local star.
     */
    private String orbital_period;
    /**
     * The diameter of this planet in kilometers.
     */
    private String diameter;
    /**
     * The climate of this planet. Comma separated if diverse.
     */
    private String climate;
    /**
     *  A number denoting the gravity of this planet, where "1" is normal or 1 standard G. "2" is twice or 2 standard Gs.
     *  "0.5" is half or 0.5 standard Gs.
     */
    private String gravity;
    /**
     * The terrain of this planet. Comma separated if diverse.
     */
    private String terrain;
    /**
     * The percentage of the planet surface that is naturally occurring water or bodies of water.
     */
    private String surface_water;
    /**
     * The average population of sentient beings inhabiting this planet.
     */
    private String population;
    /**
     * An array of People URL Resources that live on this planet.
     */
    private List<String> residents;
    /**
     * An array of Film URL Resources that this planet has appeared in.
     */
    private List<String> films;
    /**
     * the ISO 8601 date format of the time that this resource was created.
     */
    private String created;
    /**
     * the ISO 8601 date format of the time that this resource was edited.
     */
    private String edited;
    /**
     * the hypermedia URL of this resource.
     */
    private String url;
}

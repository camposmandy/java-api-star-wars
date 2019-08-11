package br.com.amandacampos.starwarsplanets.domain;

import lombok.Data;

@Data
public class Planet {
    /**
     * Identifier
     */
    private Integer id;

    /**
     * Name
     */
    private String name;

    /**
     * Climate
     */
    private String climate;

    /**
     * Terrain
     */
    private String terrain;

    /**
     * Amount of movie appearances
     */
    private Integer movieAppearances;
}

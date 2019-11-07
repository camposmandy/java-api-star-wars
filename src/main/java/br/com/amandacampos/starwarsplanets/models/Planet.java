package br.com.amandacampos.starwarsplanets.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Planet {
    /**
     * Identifier
     */
    @Id
    private String id;

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

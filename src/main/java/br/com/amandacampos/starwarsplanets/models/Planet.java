package br.com.amandacampos.starwarsplanets.models;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class Planet {
    /**
     * Identifier
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

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

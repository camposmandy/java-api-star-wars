package br.com.amandacampos.starwarsplanets.models;

import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * Response from Star Wars API.
 */
@Data
public class PlanetBase {
    /**
     * Total results found
     */
    private Integer count;

    /**
     * Pagination detail
     */
    @Nullable
    private String next;

    /**
     * Pagination detail
     */
    @Nullable
    private String previous;

    /**
     * Planet details
     */
    private List<PlanetResult> results;
}

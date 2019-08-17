package br.com.amandacampos.starwarsplanets.models;

import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * Planet from Star Wars API
 */
@Data
public class PlanetBase {
    private Integer count;
    @Nullable
    private String next;
    @Nullable
    private String previous;
    private List<PlanetResult> results;
}

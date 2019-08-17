package br.com.amandacampos.starwarsplanets.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Error implements Serializable {
    /**
     * Http status code
     */
    private Integer status;

    /**
     * Message details
     */
    private String message;

    /**
     * Path url
     */
    private String path;

    /**
     * Time
     */
    private Long timestap;
}

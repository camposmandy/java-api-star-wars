package br.com.amandacampos.starwarsplanets.services.exception;

public class PlanetNotFoundException extends RuntimeException {

    public PlanetNotFoundException(String msg) {
        super(msg);
    }
}

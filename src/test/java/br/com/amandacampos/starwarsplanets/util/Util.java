package br.com.amandacampos.starwarsplanets.util;

public class Util {

    public static Long getRandomLong() {
        long leftLimit = 1L;
        long rightLimit = 10L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }
}

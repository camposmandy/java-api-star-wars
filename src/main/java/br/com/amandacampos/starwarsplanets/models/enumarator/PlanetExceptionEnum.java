package br.com.amandacampos.starwarsplanets.models.enumarator;

public enum PlanetExceptionEnum {
    OBJ_NOT_FOUND("PLANET NOT FOUND"),
    OBJ_NOT_EXIST("PLANET DOESN'T EXIST IN THE STAR WARS API");

    final String status;

    PlanetExceptionEnum(String status) {
        this.status = status;
    }

    /**
     * Get the enum value
     * @return String
     */
    public String getStatus() {
        return this.status;
    }
}

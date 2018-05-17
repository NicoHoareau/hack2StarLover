package fr.nicolashoareau_toulousewcs.hack2starlover;

public class PlanetModel {

    private int planetPicture;
    private String namePlanete;

    public PlanetModel(){

    }

    public PlanetModel(int planetPicture, String namePlanete) {
        this.planetPicture = planetPicture;
        this.namePlanete = namePlanete;
    }

    public int getPlanetPicture() {
        return planetPicture;
    }

    public void setPlanetPicture(int planetPicture) {
        this.planetPicture = planetPicture;
    }

    public String getNamePlanete() {
        return namePlanete;
    }

    public void setNamePlanete(String namePlanete) {
        this.namePlanete = namePlanete;
    }
}

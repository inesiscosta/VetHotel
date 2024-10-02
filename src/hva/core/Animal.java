package hva.core;

public class Animal extends NamedEntity {
    private String _healthHistory;

    public Animal(String idAnminal, String name) {
        super(idAnminal, name);
    }

    public int calculateSatisfactionLevel() {
        //TODO Implement Animal.calculateSatisfactionLevel
        return 0;
    }

    protected void setHealthHistory(HealthStatus healthStatus) {
        _healthHistory += "," + healthStatus;
    }

    public String toString() {
        // TODO Implement Animal.toString
        return "";
    }

    protected void changeHabitat(Habitat newHabitat) {
        // TODO Implement Animal.changeHabitat
    }

    public String listVaccinationReccord(Hotel hotel) {
        // TODO Implement Animal.listVaccinationReccord
        return "";
    }

    protected void registerNewSpecies(String idSpecie, String name) {
        // TODO Implement Animal.registerNewSpecies
    }
}

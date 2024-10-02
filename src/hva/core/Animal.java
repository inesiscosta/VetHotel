package hva.core;

public class Animal extends NamedEntity {
    private String _healthHistory;
    private Habitat _habitat;

    public Animal(String idAnminal, String name) {
        super(idAnminal, name);
        _healthHistory = "VOID";
    }

    public int calculateSatisfactionLevel() {
        //TODO Implement Animal.calculateSatisfactionLevel
        return 0;
    }

    protected void setHealthHistory(HealthStatus healthStatus) {
        if(_healthHistory == "VOID")
            _healthHistory = null;
        _healthHistory += "," + healthStatus;
    }

    public String toString() {
        return "ANIMAL|" + this.id() + "|" + this.name() + "|" + _healthHistory + "|" + _habitat.id();
    }

    protected void changeHabitat(Habitat newHabitat) {
        // TODO Add execptions (try ctach) to Animal.changeHabitat
       Animal animal = _habitat.identifyAnimal(this.id());
       _habitat.removeAnimal(animal);
       newHabitat.addAnimal(animal);
       _habitat = newHabitat;
    }

    public String listVaccinationReccord(Hotel hotel) {
        // TODO Implement Animal.listVaccinationReccord
        return "";
    }

    protected void registerNewSpecies(String idSpecie, String name) {
        // TODO Implement Animal.registerNewSpecies
    }
}

package hva.core;

public class Animal extends NamedEntity {
    private String _healthHistory;
    private Habitat _habitat;
    private Species _species; //species singular

    public Animal(String id, String name, Species species, Habitat habitat) {
        super(id, name);
        _habitat = habitat;
        _species = species;
        _healthHistory = "VOID";
        _species.addAnimalToSpecies(this); //Adds itself to the Collection TreeSet of all Animals of the same Species that the Species holds
    }

    public int calculateSatisfactionLevel() {
        return 20 
        + 3 * _habitat.getNumAnimalSameSpecies(_species) 
        - 2 * (_habitat.getNumAnimals() - _habitat.getNumAnimalSameSpecies(_species) 
        + (_habitat.getArea() / _habitat.getNumAnimals()) 
        + _habitat.identifyInfluence(_species));
    }

    protected void updateHealthHistory(HealthStatus healthStatus) {
        if(_healthHistory == "VOID") {
            _healthHistory = healthStatus.toString();
            return;
        }
        _healthHistory += "," + healthStatus;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("ANIMAL|")
          .append(this.id()).append("|")
          .append(this.name()).append("|")
          .append(_species.id()).append("|")
          .append(_healthHistory).append("|")
          .append(_habitat.id());
        return result.toString();
    }

    protected void changeHabitat(Habitat newHabitat) {
        // TODO Add execptions (try catch) to Animal.changeHabitat
       _habitat.removeAnimal(this);
       newHabitat.addAnimal(this);
       setHabitat(newHabitat);
    }

    public String listVaccinationReccord(Hotel hotel) {
        // TODO Implement Animal.listVaccinationReccord
        return "";
    }

    protected void registerNewSpecies(String id, String name) { //Maybe remove view reason on comment from Hotel.registerNewSpecies
        // TODO Implement Animal.registerNewSpecies
    }

    protected Species species() {
        return _species;
    }

    protected Habitat geHabitat() {
        return _habitat;
    }

    private void setHabitat(Habitat habitat) {
        _habitat = habitat;
    }

    public boolean equals(Animal otherAnimal) {
        return this.id() == otherAnimal.id() && this.name() == otherAnimal.name();
    }
}

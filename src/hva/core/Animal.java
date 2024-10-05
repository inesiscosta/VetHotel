package hva.core;

public class Animal extends NamedEntity {
    private String _healthHistory;
    private Species _species;
    private Habitat _habitat;

    public Animal(String id, String name, Species species, Habitat habitat) {
        super(id, name);
        _species = species;
        _habitat = habitat;
        _healthHistory = "VOID";
        //Adds itself to the TreeSet of all Animals of the same Species that the Class Species holds
        _species.addAnimalToSpecies(this);
    }

    protected Species species() {
        return _species;
    }

    protected Habitat habitat() {
        return _habitat;
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

    protected void changeHabitat(Habitat newHabitat) throws IllegalStateException { 
        _habitat.removeAnimal(this);
        newHabitat.addAnimal(this);
        _habitat = newHabitat;
    }
}

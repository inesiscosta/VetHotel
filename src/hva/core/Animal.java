package hva.core;

public class Animal extends NamedEntity {
    private String _healthHistory;
    private Habitat _habitat;
    private Species _specie;

    public Animal(String idAnminal, String name, Species specie, Habitat habitat) {
        super(idAnminal, name);
        _habitat = habitat;
        _specie = specie;
        _healthHistory = "VOID";
    }

    public int calculateSatisfactionLevel() {
        return 20 
        + 3 * _habitat.getNumAnimalSameSpecies(_specie) 
        - 2 * (_habitat.getNumAnimals() - _habitat.getNumAnimalSameSpecies(_specie) 
        + (_habitat.getArea() / _habitat.getNumAnimals()) 
        + _habitat.identifyInfluence(_specie));
    }

    protected void setHealthHistory(HealthStatus healthStatus) {
        if(_healthHistory == "VOID")
            _healthHistory = null;
        _healthHistory += "," + healthStatus;
    }

    @Override
    public String toString() {
        return "ANIMAL|" + this.id() + "|" + this.name() + "|" + _healthHistory + "|" + _habitat.id();
    }

    protected void changeHabitat(Habitat newHabitat) {
        // TODO Add execptions (try ctach) to Animal.changeHabitat
       Animal animal = _habitat.identifyAnimal(this.id());
       _habitat.removeAnimal(animal);
       newHabitat.addAnimal(animal);
       _habitat = newHabitat; //FIXME Maybe use a seter??
    }

    public String listVaccinationReccord(Hotel hotel) {
        // TODO Implement Animal.listVaccinationReccord
        return "";
    }

    protected void registerNewSpecies(String idSpecie, String name) {
        // TODO Implement Animal.registerNewSpecies
    }

    protected Species getSpecie() {
        return _specie;
    }

    protected Habitat geHabitat() {
        return _habitat;
    }
}

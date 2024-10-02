package hva.core;

import java.util.Collection;
import java.util.TreeSet;;
public class Habitat extends NamedEntity {
    private int _area;
    private Collection<Animal> _animals;

    public Habitat(String idHabitat, String name, int area) {
        super(idHabitat, name);
        _animals = new TreeSet<Animal>();
        _area = area;
    }

    protected int getNumAnimalSameSpecies(Species species) {

        return 0;
    }

    public int identifyInfluence(Species species) {
        
        return 0;
    }

    protected int cleaningEffort() {

        return 0;
    }

    public Animal identifyAnimal(String idAnimal) {
        for(Animal animal : _animals) {
            if(animal.id().equals(idAnimal))
                return animal;
        }
        return null;
    }
    
    protected void addAnimal(Animal animal) {
        _animals.add(animal);
    }

    protected void removeAnimal(Animal animal) {
        _animals.remove(animal);
    }

    public String toString() {

        return "";
    }

    private String listTrees(Season currentSeason) {

        return "";
    }

    protected void changeHabitatInflunece(Species species, int newInfluence) {

    }

    protected void plantTree(String idTree, String name, int age, int baseCleaningDifficulty, String treeType) {

    }

    public String listAnimals() {

        return "";
    }

    public boolean equals(Habitat otherHabitat) {

        return false;
    }
    
}

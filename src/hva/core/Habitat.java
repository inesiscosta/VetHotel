package hva.core;

import java.util.Collection;
import java.util.HashMap;
import java.util.TreeSet;;

public class Habitat extends NamedEntity {
    private int _area;
    private Collection<Animal> _animals;
    private Collection<ZooKeeper> _assignedKeeper;
    private Collection<Tree> _trees;
    private HashMap<Species, Integer> _influences; //Não sei se a melhor maneira de fazer, temos de ver isto

    public Habitat(String idHabitat, String name, int area) {
        super(idHabitat, name);
        _animals = new TreeSet<Animal>();
        _influences = new HashMap<>();
        _area = area;
    }

    protected int getNumAnimalSameSpecies(Species species) {
        int numAnimalSameSpecies = 0;
        for(Animal animal : _animals) {
            if(animal.getSpecie().equals(species))  
                numAnimalSameSpecies++;
        }
        return numAnimalSameSpecies;
    }

    public int identifyInfluence(Species species) {
      try {
        return  _influences.get(species);
      } catch (NullPointerException e) {
        return 0; //TODO Returns 0 of it doesnt have a specefic influence its neutral??
      } catch (Exception e) {
        return -1; //TODO Maybe we need to catch another exceptions idknow we need to see?
      }
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

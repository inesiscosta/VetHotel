package hva.core;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Comparator;
import hva.core.exception.*;
public class Habitat extends NamedEntity {
    private int _area;
    private Map<String,Animal> _animals;
    private Collection<ZooKeeper> _assignedKeepers;
    private Collection<Tree> _trees;
    private Map<Species, Integer> _influences;

    public Habitat(String id, String name, int area) {
        super(id, name);
        _area = area;
        _animals = new TreeMap<String,Animal>(); //String is the id of the Animal.
        _assignedKeepers = new TreeSet<ZooKeeper>();
        _trees = new TreeSet<Tree>(Comparator.comparing(Tree::id));
        _influences = new HashMap<>();
    }

    int area() {
        return _area;
    }

    int getNumAnimals() {
        return _animals.size();
    }

    int getNumKeepers() {
        return _assignedKeepers.size();
    }

    public String toString(Season currentSeason) {
        StringBuilder result = new StringBuilder();
        result.append("HABITAT|")
          .append(this.id()).append("|")
          .append(this.name()).append("|")
          .append(this.area()).append("|")
          .append(_trees.size()).append("\n")
          .append(listTrees(currentSeason));
        return result.toString();
    }

    Animal identifyAnimal(String id) {
        if (_animals.containsKey(id)) //Used to prevent a exception, all exception from core handeld in the Hotel class.
            return _animals.get(id);
        return null;
    }
    
    int identifyInfluence(Species species) {
        // Returns 0 if the species isn't in the map, indicating neutral influence.
        return _influences.getOrDefault(species, 0);
    }

    void addAnimal(Animal animal) {
        _animals.put(animal.id(), animal);
    }

    void removeAnimal(Animal animal) {
        _animals.remove(animal.id());
    }

    String listAnimals() {
        StringBuilder listAnimals = new StringBuilder();
        for(Animal animal : _animals.values())
            //Needs to add a new line to generate the complete String a list of all Animals one per line.
            listAnimals.append(animal.toString()).append("\n");
        return listAnimals.toString();
    }

    int calculateAnimalsSatisfaction() {
        int satisfaction = 0;
        for (Animal animal : _animals.values())
           satisfaction += animal.calculateSatisfaction();
        return satisfaction;
    }

    int getNumAnimalSameSpecies(Species species) {
        int numAnimalSameSpecies = 0;
        for(Animal animal : _animals.values()) {
            if(animal.species().equals(species))  
                numAnimalSameSpecies++;
        }
        return numAnimalSameSpecies;
    }

    void addInfluence(Species species, int influence) {
        _influences.put(species, influence);
    }

    void changeHabitatInflunece(Species species, int newInfluence) {
        _influences.put(species, newInfluence);
    }

    void plantTree(String id, String name, int age, int baseCleaningDifficulty, TreeType treeType, Season currentSeason) throws InvalidTypeException {
        Tree tree;
        switch (treeType) {
            case EVERGREEN:
                tree = new Evergreen(id, name, age, baseCleaningDifficulty, currentSeason);
                break;
            case DECIDUOUS:
                tree = new Deciduous(id, name, age, baseCleaningDifficulty, currentSeason);
                break;
            default:
                throw new InvalidTypeException(InvalidTypeException.ErrorMessage() + treeType);
        }
        _trees.add(tree);
    }

    private String listTrees(Season currentSeason) { 
        StringBuilder listTrees = new StringBuilder();
        for(Tree tree : _trees)
            listTrees.append(tree.toString(currentSeason)).append("\n");
        return listTrees.toString();
    }

    double cleaningEffort(Season currentSeason) {
        double cleaningEffort = 0;
        for(Tree tree : _trees)
             cleaningEffort += tree.calculateCleaningEffort(currentSeason);
        return cleaningEffort;
    }
}

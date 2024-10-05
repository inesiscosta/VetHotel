package hva.core;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Comparator;

//Think about immutable lists
public class Habitat extends NamedEntity {
    private int _area;
    private Map<String,Animal> _animals;
    private Collection<ZooKeeper> _assignedKeepers;
    private Collection<Tree> _trees;
    private Map<Species, Integer> _influences;

    public Habitat(String id, String name, int area) {
        super(id, name);
        _area = area;
        _animals = new TreeMap<String,Animal>(); //Note comparing by animal
        _assignedKeepers = new TreeSet<ZooKeeper>();
        _trees = new TreeSet<Tree>(Comparator.comparing(Tree::id));
        _influences = new HashMap<>();
    }

    protected int getNumAnimalSameSpecies(Species species) {
        int numAnimalSameSpecies = 0;
        for(Animal animal : _animals.values()) {
            if(animal.getSpecies().equals(species))  
                numAnimalSameSpecies++;
        }
        return numAnimalSameSpecies;
    }

    protected void addInfluence(Species species, int influence) {
        _influences.put(species, influence);
    }

    public int identifyInfluence(Species species) {
        return _influences.getOrDefault(species, 0); // Returns 0 if the species isn't in the map, indicating neutral influence
    }

    protected double cleaningEffort(Season currentSeason) {
        double cleaningEffort = 0;
        for(Tree tree : _trees)
             cleaningEffort += tree.calculateCleaningEffort(currentSeason);
        return cleaningEffort;
    }

    protected double habitatWork(Season currentSeason) {
        return this.getArea() + 3 * _animals.size() + this.cleaningEffort(currentSeason);
    }

    public Animal identifyAnimal(String id) {
       return _animals.get(id);
    }
    
    protected void addAnimal(Animal animal) {
        _animals.put(animal.id(), animal);
    }

    protected void removeAnimal(Animal animal) {
        _animals.remove(animal.id());
    }

    public String toString(Season currentSeason) {
        StringBuilder result = new StringBuilder();
        result.append("HABITAT|")
          .append(this.id()).append("|")
          .append(this.name()).append("|")
          .append(this.getArea()).append("|")
          .append(_trees.size()).append("\n")
          .append(listTrees(currentSeason));
        return result.toString();
    }

    private String listTrees(Season currentSeason) { 
        StringBuilder listTrees = new StringBuilder();
        for(Tree tree : _trees)
            listTrees.append(tree.toString(currentSeason)).append("\n");
        return listTrees.toString();
    }

    protected void changeHabitatInflunece(Species species, int newInfluence) {
        _influences.put(species, newInfluence);
    }

    protected void plantTree(String id, String name, int age, int baseCleaningDifficulty, TreeType treeType, Season currentSeason) throws IllegalArgumentException {
        Tree tree;
        switch (treeType) {
            case EVERGREEN:
                tree = new Evergreen(id, name, age, baseCleaningDifficulty, currentSeason);
                break;
            case DECIDUOUS:
                tree = new Deciduous(id, name, age, baseCleaningDifficulty, currentSeason);
                break;
            default:
                throw new IllegalArgumentException("Invalid tree type"); //Check this exception.
        }
        _trees.add(tree);
    }

    public String listAnimals() {
        StringBuilder listAnimals = new StringBuilder();
        for(Animal animal : _animals.values())
            listAnimals.append(animal.toString()).append("\n"); //Needs to add a new line to generate the complete String a list of all Animals one per line
        return listAnimals.toString();
    }

    protected int getNumAnimals() {
        return _animals.size();
    }

    protected int getArea() {
        return _area;
    }
}

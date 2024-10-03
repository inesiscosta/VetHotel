package hva.core;

import java.util.Collection;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class Habitat extends NamedEntity {
    private int _area;
    private Collection<Animal> _animals;
    private Collection<ZooKeeper> _assignedKeepers;
    private Collection<Tree> _trees;
    private HashMap<Species, Integer> _influences;
    public Habitat(String idHabitat, String name, int area) {
        super(idHabitat, name);
        _animals = new TreeSet<Animal>();
        _assignedKeepers = new TreeSet<ZooKeeper>();
        _trees = new TreeSet<Tree>();
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

    protected int cleaningEffort() { //TODO Maybe change name to trabalho_no_habitat something related
        double cleaningEffortTree = 0;
        for(Tree tree : _trees) {
             cleaningEffortTree += tree.calculateCleaningEffort();
        }
        int cleaningEffort = (int) Math.round(cleaningEffortTree);
        return _area + 3 * _animals.size() + cleaningEffort;
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

    @Override
    public String toString() {
        return "HABITAT|" + this.id() + "|" + this.name() + "|" + String.valueOf(_area) + "|" + String.valueOf(_trees.size());
    }

    private String listTrees(Season currentSeason) { //FIXME We dont have acess to currentSeason unless it is called from the Hotel
        StringBuilder listTrees = new StringBuilder();                        //Or we make this public or we pass currentSeason to the toString of the Habitat
        List<Tree> treeOrderList = new ArrayList<>(_trees);
        treeOrderList.sort(Comparator.comparing(Tree::id));
        for(Tree tree : treeOrderList) {
            listTrees.append(tree.toString(currentSeason)).append("\n");
        }
        return listTrees.toString();
    }

    protected void changeHabitatInflunece(Species species, int newInfluence) {
        _influences.put(species, newInfluence);
    }

    protected void plantTree(String idTree, String name, int age, int baseCleaningDifficulty, String treeType) {
        if(treeType == "EVERGREEN") {
            Tree tree = new Evergreen(idTree, name, baseCleaningDifficulty, null); //FIXME Where do we get the currentSeason maybe pass from the hotel
            _trees.add(tree);
            return;
        }
        Tree tree = new Decidious(idTree, name, baseCleaningDifficulty, null); //FIXME Where do we get the currentSeason maybe pass from the hotel
        _trees.add(tree);
        return;
    }

    public String listAnimals() {
        StringBuilder listAnimals = new StringBuilder();
        List<Animal> animalOrderList = new ArrayList<>(_animals);
        animalOrderList.sort(Comparator.comparing(Animal::id));
        for(Animal animal : animalOrderList) {
            listAnimals.append(animal.toString()).append("\n"); //Needs to add a new line to generate the complete String a list of all Animals one per line
        }
        return listAnimals.toString();
    }

    public boolean equals(Habitat otherHabitat) {
        return this.id().equals(otherHabitat.id()); //TODO Check if we shouldnt put this equals in the NamedEntity
    }
    
    protected int getNumAnimals() {
        return _animals.size();
    }

    protected int getArea() {
        return _area;
    }
}

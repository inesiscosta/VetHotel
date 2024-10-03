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
    private Collection<ZooKeeper> _assignedKeeper;
    private Collection<Tree> _trees;
    private HashMap<Species, Integer> _influences; //TODO Não sei se a melhor maneira de fazer, temos de ver isto

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
        return "HABITAT|" + this.id() + "|" + this.name() + "|" + String.valueOf(_area) + "|" + String.valueOf(_trees.size());
    }

    private String listTrees(Season currentSeason) { //FIXME We dont have acess to currentSeason unless it is called from the Hotel
        String listTrees = null;                        //Or we make this public or we pass currentSeason to the toString of the Habitat
        List<Tree> treeOrderList = new ArrayList<>(_trees);
        treeOrderList.sort(Comparator.comparing(Tree::id));
        for(Tree tree : treeOrderList) {
            listTrees += "\n" + tree.toString(currentSeason);
        }
        return listTrees;
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
        String listAnimals = null;
        List<Animal> animalOrderList = new ArrayList<>(_animals);
        animalOrderList.sort(Comparator.comparing(Animal::id));
        for(Animal animal : animalOrderList) {
            listAnimals += "\n" + animal.toString(); //Needs to add a new line to generate the complete String a list of all Animals one per line
        }
        return listAnimals;
    }

    public boolean equals(Habitat otherHabitat) {
        return this.id().equals(otherHabitat.id()); //TODO Check if we shouldnt put this equals in the NamedEntity
    }
    
}

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

        return null;
    }

    public int identifyInfluence(Species species) {
        
        return null;
    }

    protected int cleaningEffort() {

        return null;
    }

    public Animal identifyAnimal(String idAnimal) {
        for(Animal animal : _animals) {
            if(animal.id().equals(idAnimal))
                return a;
        }
        return null;
    }
    
    protected void addAnimal(Animal animal) {
        _animals.add(animal);
    }

    protected void removeAnimal(Animal animal) {
        _animals.remove(animal);
    }
    
}

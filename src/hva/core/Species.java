package hva.core;
import java.util.Collection;
import java.util.TreeSet;
import java.util.HashSet;
public class Species extends NamedEntity{
    private Collection<Animal> _animals;
    private Collection<Veterinarian> _qualifiedVets;
    
    public Species(String id, String name) {
        super(id, name);
        _animals = new TreeSet<Animal>();
        _qualifiedVets = new HashSet<Veterinarian>();
    }

    int getNumAnimals() {
        return _animals.size();
    }

    int getNumQualifiedVets() {
        return _qualifiedVets.size();
    }

    void addAnimal(Animal animal) {
        _animals.add(animal);
    }
}

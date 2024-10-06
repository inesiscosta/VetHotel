package hva.core;
import java.util.Collection;
import java.util.TreeSet;
import java.util.HashSet;
public class Species extends NamedEntity{
    private Collection<Animal> _animals;
    private Collection<Veterinarian> _qualifiedVets; // Teacher doesn't have this association in his UML diagram. How to calculate vet's satisfaction?
    
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

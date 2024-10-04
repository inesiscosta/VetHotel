package hva.core;
import java.util.Collection;
import java.util.TreeSet;
import java.util.HashSet;
public class Species extends NamedEntity{
      
    private Collection<Animal> _animals;
    private Collection<Veterinary> _qualifiedVets;
    
    public Species(String idSpecies, String name) {
        super(idSpecies, name);
        _animals = new TreeSet<Animal>();
        _qualifiedVets = new HashSet<Veterinary>();
    }

    public boolean equals(Species otherSpecies) {
        return this.id().equals(otherSpecies.id());
    }

    protected void addAnimalToSpecies(Animal animal) { //FIXME Made sense in my head dont know yet if it is needed
        _animals.add(animal);
    }

    //TODO Maybe not the best way see Hotel.addResponsability
    protected void addQualifiedVet(Veterinary vet) {
        _qualifiedVets.add(vet);
    }

    protected void removeQualifiedVet(Veterinary vet) {
        _qualifiedVets.remove(vet);
    }
}

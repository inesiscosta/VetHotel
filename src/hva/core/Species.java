package hva.core;
import java.util.Collection;
import java.util.TreeSet;
public class Species extends NamedEntity{
      
    private Collection<Animal> _animals;
    
    public Species(String idSpecies, String name) {
        super(idSpecies, name);
        _animals = new TreeSet<Animal>();
    }

    public boolean equals(Species otherSpecies) {
        return this.id().equals(otherSpecies.id());
    }

    protected void addAnimalToSpecies(Animal animal) { //FIXME Made sense in my head dont know yet if it is needed
        _animals.add(animal);
    }
}

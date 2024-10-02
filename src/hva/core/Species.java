package hva.core;
import java.util.Collection;
import java.util.TreeSet;
public class Species extends NamedEntity{
      
    private Collection<Animal> _animals; //FIXME Maybe we need some gets and seter for _animals
    
    public Species(String idSpecies, String name) {
        super(idSpecies, name);
        _animals = new TreeSet<Animal>();
    }

    public boolean equals(Species otherSpecies) {
        return this.id().equals(otherSpecies.id());
    }
}

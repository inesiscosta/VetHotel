package hva.core;

public class Species extends NamedEntity{
      
    private Collection<Animal> _animals;
    
    public Species(String idSpecies, String name) {
        super(idSpecies, name);
        _animals = new TreeSet<Animal>();
    }

    public boolean equals(Species otherSpecies) {
        return this.id.equals(otherSpecies.id);
    }
}

package hva.core;

public class Species extends NamedEntity{

    public Species(String idSpecies, String name) {
        super(idSpecies, name);
    }

    public boolean equals(Species otherSpecies) {
        return _idSpecies.equals(otherSpecies._idSpecies);
    }
}

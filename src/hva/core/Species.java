package hva.core;

public class Species {
    private String _idSpecies;
    private String _name;

    public Species(String idSpecies, String name) {
        _idSpecies = idSpecies;
        _name = name;
    }

    public boolean equals(Species otherSpecies) {
        return _idSpecies.equals(otherSpecies._idSpecies);
    }
}

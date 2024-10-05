package hva.core;

import java.util.Collection;
import java.util.TreeSet;

public class Vaccine extends NamedEntity{
    private int _numApplications;
    private Collection<Species> _appropiateSpecies;

    public Vaccine(String id, String name) {
        super(id, name);
        _appropiateSpecies = new TreeSet<Species>();
    }

    protected HealthStatus determineVaccineEffect(Animal animal) throws IllegalStateException {
        boolean correctSpecies = _appropiateSpecies.contains(animal.species());
        return HealthStatus.determineHealthStatus(calculateVaccineDamage(animal), correctSpecies);
    }

    private int calculateVaccineDamage(Animal animal){
        Species speciesBiggestName =  biggestSpeciesName();
        int commonCharacters = 0;
        String speciesName = animal.species().name();
        for (int i = 0; i < Math.min(speciesBiggestName.name().length(), speciesName.length()); i++) {
            if (speciesBiggestName.name().charAt(i) == speciesName.charAt(i))
            commonCharacters++;
        }
        return biggestSpeciesName().name().length() - commonCharacters;
    }

    private Species biggestSpeciesName(){
        Species biggestSpecies = null;
        for (Species species : _appropiateSpecies) {
            if (biggestSpecies == null || species.name().length() > biggestSpecies.name().length())
                biggestSpecies = species;
        }
        return biggestSpecies;
    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("VACINA|")
          .append(this.id()).append("|")
          .append(this.name()).append("|")
          .append(_numApplications).append("|")
          .append(suitableSpeciesToString());
        return result.toString();
    }

    private String suitableSpeciesToString(){
        StringBuilder suitableSpecies = new StringBuilder();
        for (Species specie: _appropiateSpecies)
            suitableSpecies.append( specie.id()).append(",");
        return suitableSpecies.toString();
    }

}

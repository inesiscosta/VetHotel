package hva.core;

import java.util.Collection;
import java.util.TreeSet;

public class Vaccine extends NamedEntity{

    private int _numApplications;
    private Collection<Species> _appropiateSpecies;

    public Vaccine(String idVaccine, String name) {
        super(name, idVaccine);
        _appropiateSpecies = new TreeSet<Species>();
    }

    protected HealthStatus determineVaccineEffect(Animal animal){
        // TODO Implement Vaccine.determineVaccineEffect
        return null;
    }

    private int calculateVaccineDamage(Animal animal){
        // TODO Implement Vaccine.calculateVaccineDamage
        return 0;
    }

    private Species biggestSpeciesName(){
        // TODO Implement Vaccine.biggestSpeciesName
        return null;
    }

    public String toString(){
        return "VACINA|" + this.id() + "|" + this.name() + "|" + _numApplications + "|" + suitableSpeciesToString();

    }

    private String suitableSpeciesToString(){
        StringBuilder suitableSpecies = new StringBuilder();
        for( Species specie: _appropiateSpecies) {
            suitableSpecies.append( specie.id()).append(",");
        }
        return suitableSpecies.toString();
    }

}

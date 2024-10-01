package hva.core;

public class Vaccine {
    private String _idVaccine;
    private String _name;
    private int _numApplications;

    public Vaccine(String idVaccine, String name) {
        _idVaccine = idVaccine;
        _name = name;
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
        // TODO Implement Vaccine.toString
        return null;

    }

    private String suitableSpeciesToString(){
        // TODO Implement Vaccine.suitableSpeciesToString
        return null;
    }

}

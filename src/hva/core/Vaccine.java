package hva.core;

public class Vaccine extends NamedEntity{

    private int _numApplications;

    public Vaccine(String idVaccine, String name) {
        super(name, idVaccine);
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

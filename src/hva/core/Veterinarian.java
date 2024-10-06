package hva.core;
import java.util.Collection;
import java.util.HashSet;

import hva.core.exception.*;

public class Veterinarian extends Employee{
    private Collection<Species> _knowsHowToVaccinate;

    public Veterinarian(String idEmployee, String name) {
        super(idEmployee, name, EmployeeType.VETERINARIAN);
        _knowsHowToVaccinate = new HashSet<Species>();
    }
    
    @Override
    double calculateSatisfaction() {
        int work = 0;
        for (Species species : _knowsHowToVaccinate)
            work += (species.getNumAnimals()) / species.getNumQualifiedVets();
        return 300 - work;
    }

    @Override
    void addResponsibility(String id) throws UnknowIdException{
        _knowsHowToVaccinate.add(this.hotel().identifySpecies(id));
    }

    @Override
    void removeResponsibility(String id) throws UnknowIdException{
        _knowsHowToVaccinate.remove(this.hotel().identifySpecies(id));
    }

    @Override
    String getIdResponsibilities() {
        String idResponsibilities = null;
        for (Species species : _knowsHowToVaccinate)
            idResponsibilities += species.id();
        return idResponsibilities;
    }

    VaccinationRecord vaccinate(Vaccine vaccine, Animal animal){
        HealthStatus animalHealthStatus = vaccine.determineVaccineEffect(animal);
        animal.updateHealthHistory(animalHealthStatus);
        return new VaccinationRecord(vaccine, this, animal);
    }
}

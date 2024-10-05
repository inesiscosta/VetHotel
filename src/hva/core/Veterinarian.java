package hva.core;
import java.util.Collection;
import java.util.HashSet;

public class Veterinarian extends Employee{
    private Collection<Species> _knowsHowToVaccinate;

    public Veterinarian(String idEmployee, String name, EmployeeType employeeType) {
        super(idEmployee, name, employeeType);
        _knowsHowToVaccinate = new HashSet<Species>();
    }
    
    @Override
    public double calculateSatisfactionLevel() {
        int work = 0;
        for (Species species : _knowsHowToVaccinate) {
            work += (species.getNumAnimals()) / species.getNumQualifiedVets();
        }
        return 300 - work;
    }

    @Override
    protected void addResponsibility(String id) {
        _knowsHowToVaccinate.add(this.getHotel().identifySpecies(id));
    }

    @Override
    protected void removeResponsibility(String id) {
        _knowsHowToVaccinate.remove(this.getHotel().identifySpecies(id));
    }

    @Override
    public String getIdResponsibilities() {
        String idResponsibilities = null;
        for (Species species : _knowsHowToVaccinate)
            idResponsibilities += species.id();
        return idResponsibilities;
    }

    protected VaccinationRecord vaccinate(Vaccine vaccine, Animal animal){
        return new VaccinationRecord(vaccine, this, animal);
    }
}

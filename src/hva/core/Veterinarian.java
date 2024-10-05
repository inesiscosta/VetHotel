package hva.core;
import java.util.Collection;
import java.util.HashSet;

public class Veterinarian extends Employee{
    private Collection<Species> _knowsHowToVaccinate;

    public Veterinarian(String idEmployee, String name, String employeeType) {
        super(idEmployee, name, employeeType);
        _knowsHowToVaccinate = new HashSet<Species>();
    }
    
    @Override
    public int calculateSatisfactionLevel() {
        return 0;
    }

    @Override
    public String toString() {
        return  this.employeeType() + " | " + this.id() + " | " + this.name() + " | "; // FIXME: add more attributes
    }

    protected void addSpecies(Species species){
        _knowsHowToVaccinate.add(species);
    }

    protected void removeSpecies(Species species){
        _knowsHowToVaccinate.remove(species);
    }

    protected VaccinationRecord vaccinate(Vaccine vaccine, Animal animal){
        return null;
    }
}

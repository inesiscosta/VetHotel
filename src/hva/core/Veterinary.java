package hva.core;

public class Veterinary extends Employee{

    public Veterinary(String idEmployee, String name, String employeeType) {
        super(idEmployee, name, employeeType);
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
        
    }

    protected void removeSpecies(Species species){
        
    }

    protected VaccinationRecord vaccinate(Vaccine vaccine, Animal animal){
        return null;
    }
}

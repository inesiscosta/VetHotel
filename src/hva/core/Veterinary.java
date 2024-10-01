package hva.core;

public class Veterinary extends Employee{

    public Veterinary(String idEmployee, String name, String employeeType) {
        super(idEmployee, name, employeeType);
    }
    
    public int calculateSatisfactionLevel() {
        return 0;
    }

    public String toString() {
        return null;
    }

    protected void addSpecies(Species species){
        
    }

    protected void removeSpecies(Species species){
        
    }

    protected VaccinationRecord vaccinate(Vaccine vaccine, Animal animal){
        return null;
    }
}

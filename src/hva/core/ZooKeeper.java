package hva.core;

public class ZooKeeper extends Employee{
    public ZooKeeper(String idEmployee, String name, String employeeType) {
        super(idEmployee, name, employeeType);
    }

    public int calculateSatisfactionLevel() {
        return 0;
    }

    public String toString() {
        return null;
    }

    protected void addHabitat(Habitat habitat){

    }

    protected void removeHabitat(Habitat habitat){

    }

    private int workEffort(){
        // TODO Implement ZooKeeper.workEffort
        return 0;
    }
}

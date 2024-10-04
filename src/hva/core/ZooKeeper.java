package hva.core;
import java.util.Collection;
import java.util.HashSet;

public class ZooKeeper extends Employee{
    private Collection<Habitat> __assignedHabitats;

    public ZooKeeper(String idEmployee, String name, String employeeType) {
        super(idEmployee, name, employeeType);
        __assignedHabitats = new HashSet<Habitat>();
    }

    @Override
    public int calculateSatisfactionLevel() {
        return 0;
    }

    @Override
    public String toString() {
        return null;
    }

    protected void addHabitat(Habitat habitat){
        __assignedHabitats.add(habitat);
    }

    protected void removeHabitat(Habitat habitat){
        __assignedHabitats.remove(habitat);
    }

    private int workEffort(){
        // TODO Implement ZooKeeper.workEffort
        return 0;
    }
}

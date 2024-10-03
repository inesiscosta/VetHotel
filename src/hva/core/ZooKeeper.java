package hva.core;
import java.util.Collection;
import java.util.HashSet;

public class ZooKeeper extends Employee{
    private Collection<Habitat> _habitat;

    public ZooKeeper(String idEmployee, String name, String employeeType) {
        super(idEmployee, name, employeeType);
        _habitat = new HashSet<Habitat>();
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
        _habitat.add(habitat);
    }

    protected void removeHabitat(Habitat habitat){
        _habitat.remove(habitat);
    }

    private int workEffort(){
        // TODO Implement ZooKeeper.workEffort
        return 0;
    }
}

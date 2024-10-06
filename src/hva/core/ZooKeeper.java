package hva.core;
import java.util.Collection;
import java.util.HashSet;

public class ZooKeeper extends Employee{
    private Collection<Habitat> _assignedHabitats;

    public ZooKeeper(String idEmployee, String name) {
        super(idEmployee, name, EmployeeType.ZOOKEEPER);
        _assignedHabitats = new HashSet<Habitat>();
    }

    @Override
    double calculateSatisfaction() {
        double work = 0;
        for (Habitat habitat : _assignedHabitats)
            work += (workEffort(habitat) / habitat.getNumKeepers());
        return 300 - work;
    }

    private double workEffort(Habitat habitat) {
        return habitat.area() 
        + 3 * habitat.getNumAnimals() 
        + habitat.cleaningEffort(hotel().currentSeason());
    }

    @Override
    void addResponsibility(String id) {
        _assignedHabitats.add(this.hotel().identifyHabitat(id));
    }

    @Override
    void removeResponsibility(String id) {
        _assignedHabitats.remove(this.hotel().identifyHabitat(id));
    }

    @Override
    String getIdResponsibilities() {
        StringBuilder idResponsibilities = new StringBuilder();
        for (Habitat habitat : _assignedHabitats)
            idResponsibilities.append(habitat.id());
        return idResponsibilities.toString();
    }
}

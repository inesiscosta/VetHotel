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
    public double calculateSatisfactionLevel() {
        double work = 0;
        for (Habitat habitat : _assignedHabitats) {
            work += (workEffort(habitat) / habitat.getNumKeepers());
        }
        return 300 - work;
    }

    private double workEffort(Habitat habitat) {
        return habitat.getArea() + 3 * habitat.getNumAnimals() + habitat.cleaningEffort(getHotel().getCurrentSeason());
    }

    @Override
    protected void addResponsibility(String id) {
        _assignedHabitats.add(this.getHotel().identifyHabitat(id));
    }

    @Override
    protected void removeResponsibility(String id) {
        _assignedHabitats.remove(this.getHotel().identifyHabitat(id));
    }

    @Override
    public String getIdResponsibilities() {
        String idResponsibilities = null;
        for (Habitat habitat : _assignedHabitats)
            idResponsibilities += habitat.id();
        return idResponsibilities;
    }
}

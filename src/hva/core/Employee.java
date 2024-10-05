package hva.core;

public abstract class Employee extends NamedEntity{
    private final EmployeeType _employeeType;
    private Hotel _hotel;

    public Employee(String id, String name, EmployeeType employeeType) {
        super(id, name);
        _employeeType = employeeType;
    }

    EmployeeType type() {
        return _employeeType;
    }

    public Hotel hotel() {
        return _hotel;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(this.type())
            .append("|")
            .append(this.id())
            .append("|")
            .append(this.name());
        String responsibilities = this.getIdResponsibilities();
        if (responsibilities != null)
            result.append("|").append(responsibilities);
        return result.toString();
    }

    abstract double calculateSatisfaction();

    abstract void addResponsibility(String id);

    abstract void removeResponsibility(String id);

    abstract String getIdResponsibilities();
}

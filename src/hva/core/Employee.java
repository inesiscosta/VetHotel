package hva.core;

public abstract class Employee extends NamedEntity{
    private final EmployeeType _employeeType;
    private Hotel _hotel;

    public Employee(String id, String name, EmployeeType employeeType) {
        super(id, name);
        _employeeType = employeeType;
    }

    protected EmployeeType getEmployeeType() {
        return _employeeType;
    }

    public Hotel getHotel() {
        return _hotel;
    }

    public abstract double calculateSatisfactionLevel();

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(this.getEmployeeType())
          .append("|")
          .append(this.id())
          .append("|")
          .append(this.name());
        
        String responsibilities = this.getIdResponsibilities();
        if (responsibilities != null)
            result.append("|").append(responsibilities);
        return result.toString();
    }

    abstract protected String getIdResponsibilities();

    protected abstract void addResponsibility(String id);

    protected abstract void removeResponsibility(String id);
}

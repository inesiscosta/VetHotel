package hva.core;

public abstract class Employee extends NamedEntity{
    private String _employeeType;
    private Hotel _hotel;

    public Employee(String idEmployee, String name, String employeeType) {
        super(idEmployee, name);
        _employeeType = employeeType;
    }

    public abstract int calculateSatisfactionLevel();

    public abstract String toString();

    protected String employeeType(){
        return _employeeType;
    }
}

package hva.core;

public abstract class Employee {
    private String _idEmployee;
    private String _name;
    private String _employeeType;

    public Employee(String idEmployee, String name, String employeeType) {
        _idEmployee = idEmployee;
        _name = name;
        _employeeType = employeeType;
    }

    public abstract int calculateSatisfactionLevel();

    public abstract String toString();
}

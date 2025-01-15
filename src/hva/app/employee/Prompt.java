package hva.app.employee;

public interface Prompt {
  static String employeeKey() {
    return "Employee ID: ";
  }
  
  static String employeeName() {
    return "Employee Name: ";
  }
  
  static String employeeType() {
    return "Type of Employee? (VET or ZKP) ";
  }
  
  static String responsibilityKey() {
    return "Responsibility ID: ";
  }
}

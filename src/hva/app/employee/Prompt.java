package hva.app.employee;

public interface Prompt {
  static String employeeKey() {
    return "Identificador único do funcionário: ";
  }
  
  static String employeeName() {
    return "Nome do funcionário: ";
  }
  
  static String employeeType() {
    return "Tipo do funcionário? (VET ou TRT) ";
  }
  
  static String responsibilityKey() {
    return "Identificador único da responsabilidade: ";
  }
}

package hva.core.exception;

/**
 * Class for representing an employee not responsible exception.
 */
public class EmployeeNotResponsibleException extends Exception {

  private static final String ERROR_MESSAGE = "Employee is not responsible for: ";
  
  /**
   * Constructor for the exception.
   * 
   * @param id the id of the reponsibility the employee is not responsible for
   **/
  public EmployeeNotResponsibleException(String id) {
    super(ERROR_MESSAGE + id);
  }

  /**
   * Alternative constructor for the exception.
   * 
   * @param id the id of the reponsibility the employee is not responsible for
   * @param cause The cause of the exception
   **/
  public EmployeeNotResponsibleException(String id, Exception cause) {
    super(ERROR_MESSAGE + id, cause);
  }
}

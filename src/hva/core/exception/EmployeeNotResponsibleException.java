package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an employee not responsible exception.
 */
public class EmployeeNotResponsibleException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * Constructor for the exception.
   * 
   * @param id the id of the reponsibility the employee is not responsible for
   **/
  public EmployeeNotResponsibleException(String id) {
    super("Employee is not responsible for:" + id);
  }

  /**
   * Alternative constructor for the exception.
   * 
   * @param id the id of the reponsibility the employee is not responsible for
   * @param cause The cause of the exception
   **/
  public EmployeeNotResponsibleException(String id, Exception cause) {
    super("Employee is not responsible for:" + id, cause);
  }
}

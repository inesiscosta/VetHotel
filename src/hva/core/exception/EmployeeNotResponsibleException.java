package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an invalid ID entry.
 */
public class EmployeeNotResponsibleException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param message the error message
   **/
  public EmployeeNotResponsibleException(String id) {
    super("Employee is not responsible for:" + id);
  }

    /**
   * @param message the error message
   * @param cause The cause of the exception
   **/
  public EmployeeNotResponsibleException(String id, Exception cause) {
    super("Employee is not responsible for:" + id, cause);
  }
}

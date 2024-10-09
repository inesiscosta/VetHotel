package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an invalid ID entry.
 */
public class UnknownEmployeeIdException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param message the error message
   **/
  public UnknownEmployeeIdException(String id) {
    super("Employee doesnt exist: " + id);
  }

    /**
   * @param message the error message
   * @param cause The cause of the exception
   **/
  public UnknownEmployeeIdException(String id, Exception cause) {
    super("Employee doesnt exist: " + id, cause);
  }
}

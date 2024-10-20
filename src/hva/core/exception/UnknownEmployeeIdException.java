package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an unknown employee id exception.
 */
public class UnknownEmployeeIdException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * Constructor for the exception.
   * 
   * @param id the id that doesnt exist
   **/
  public UnknownEmployeeIdException(String id) {
    super("Employee doesnt exist: " + id);
  }

  /**
   * Alternative constructor for the exception.
   * 
   * @param id the id that doesnt exist
   * @param cause The cause of the exception
   **/
  public UnknownEmployeeIdException(String id, Exception cause) {
    super("Employee doesnt exist: " + id, cause);
  }
}

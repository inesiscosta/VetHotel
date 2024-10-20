package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an invalid employee type exception.
 **/
public class InvalidEmployeeTypeException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param message The error message
   **/
  public InvalidEmployeeTypeException(String type) {
    super("Invalid Employee type: " + type);
  }
}

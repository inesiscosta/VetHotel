package hva.core.exception;

/**
 * Class for representing an invalid employee type exception.
 **/
public class InvalidEmployeeTypeException extends Exception {
  /**
   * @param message The error message
   **/
  public InvalidEmployeeTypeException(String type) {
    super("Invalid Employee type: " + type);
  }
}

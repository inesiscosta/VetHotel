package hva.core.exception;

import java.io.Serial;

  /**
   * Class for representing an invalid type, tree or employee.
   **/
public class InvalidTypeException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  private static final String ERROR_MESSAGE = "Invalid Tree type: ";
  private static final String ERROR_MESSAGE_EMPLOYEE = "Invalid Employee type: ";
  
  
  /**
   * @param message The error message
   **/
  public InvalidTypeException(String message) {
    super(message);
  }

      /**
   * Used to construct the error message
   **/
  public static String ErrorMessage() {
      return ERROR_MESSAGE;
  }

      /**
   * Used to construct the error message
   **/
  public static String ErrorMessageEmployee() {
      return ERROR_MESSAGE_EMPLOYEE;
  }

}

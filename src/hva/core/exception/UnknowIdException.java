package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an invalid ID entry.
 */
public class UnknowIdException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  private static final String ERROR_MESSAGE = "Id not found: ";
  private static final String ERROR_MESSAGE_HABITAT = "Habitat doesnt exists: ";
  private static final String ERROR_MESSAGE_EMPLOYEE = "Employee doesnt exists: ";
  private static final String ERROR_MESSAGE_SPECIES = "Species doesnt exists: ";

  
  /**
   * @param message the error message
   **/
  public UnknowIdException(String message) {
    super(message);
  }

    /**
   * @param message the error message
   * @param cause The cause of the exception
   **/
  public UnknowIdException(String message, Exception cause) {
    super(message, cause);
  }

    /**
   * Used to construct the error message
   **/
  public static String errorMessage() {
    return ERROR_MESSAGE;
  }

    /**
   * Used to construct the error message
   **/
  public static String errorMessageHabitat() {
    return ERROR_MESSAGE_HABITAT;
  }

      /**
   * Used to construct the error message
   **/
  public static String errorMessageEmployee() {
    return ERROR_MESSAGE_EMPLOYEE;
  }
    /**
   * Used to construct the error message
   **/
  public static String errorMessageSpecies() {
    return ERROR_MESSAGE_SPECIES;
  }
}

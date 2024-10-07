package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an Duplicated Id entry.
 */
public class DuplicatedIdException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  private static final String ERROR_MESSAGE = "Id already exists: ";
  private static final String ERROR_MESSAGE_SPECIES_EXISTS = "The Species already exists: ";
  private static final String ERROR_MESSAGE_EMPLOYEE_EXISTS = "The Employee already exists: ";
  private static final String ERROR_MESSAGE_VACCINE_EXISTS = "The Vaccine already exists: ";
  private static final String ERROR_MESSAGE_HABITAT_EXISTS = "The Habitat already exists: ";
  
  
    /**
   * @param message the id that already exists
   **/
  public DuplicatedIdException(String message) {
    super(message);
  }

  /**
   * @param message the id that already exists
   * @param cause exception that triggered this one
   **/
  public DuplicatedIdException(String message, Exception cause) {
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
  public static String errorMessageSpecies() {
    return ERROR_MESSAGE_SPECIES_EXISTS;
  }

    /**
   * Used to construct the error message
   **/
  public static String errorMessageEmployee() {
    return ERROR_MESSAGE_EMPLOYEE_EXISTS;
  }

   /**
   * Used to construct the error message
   **/
  public static String errorMessageVaccine() {
    return ERROR_MESSAGE_VACCINE_EXISTS;
  }

      /**
   * Used to construct the error message
   **/
  public static String errorMessageHabitat() {
    return ERROR_MESSAGE_HABITAT_EXISTS;
  }
}

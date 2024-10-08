package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an Duplicated Id entry.
 */
public class DuplicateIdException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  private static final String ERROR_MESSAGE = "Id already exists: ";
  private static final String ERROR_MESSAGE_SPECIES_EXISTS = "Species already exists: ";
  private static final String ERROR_MESSAGE_EMPLOYEE_EXISTS = "Employee already exists: ";
  private static final String ERROR_MESSAGE_VACCINE_EXISTS = "Vaccine already exists: ";
  private static final String ERROR_MESSAGE_HABITAT_EXISTS = "Habitat already exists: ";
  private static final String ERROR_MESSAGE_ANIMAL_EXISTS = "ANIMAL already exists: ";
  
  
    /**
   * @param message the id that already exists
   **/
  public DuplicateIdException(String message) {
    super(message);
  }

  /**
   * @param message the id that already exists
   * @param cause exception that triggered this one
   **/
  public DuplicateIdException(String message, Exception cause) {
    super(message, cause);
  }

    /**
   * Used to construct the error message
   **/
  public static String errorMessage() {
    return ERROR_MESSAGE;
  }

    /**
   * Used to construct the error message for Species
   **/
  public static String errorMessageSpecies() {
    return ERROR_MESSAGE_SPECIES_EXISTS;
  }

    /**
   * Used to construct the error message for Employye
   **/
  public static String errorMessageEmployee() {
    return ERROR_MESSAGE_EMPLOYEE_EXISTS;
  }

   /**
   * Used to construct the error message for Vaccine
   **/
  public static String errorMessageVaccine() {
    return ERROR_MESSAGE_VACCINE_EXISTS;
  }

      /**
   * Used to construct the error message for Habitat
   **/
  public static String errorMessageHabitat() {
    return ERROR_MESSAGE_HABITAT_EXISTS;
  }

      /**
   * Used to construct the error message for Animal
   **/
  public static String errorMessageAnimal() {
    return ERROR_MESSAGE_ANIMAL_EXISTS;
  }
}

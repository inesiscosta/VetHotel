package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an invalid ID entry.
 */
public class UnknownHabitatIdException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param message the error message
   **/
  public UnknownHabitatIdException(String id) {
    super("Habitat doesnt exist: " + id);
  }

    /**
   * @param message the error message
   * @param cause The cause of the exception
   **/
  public UnknownHabitatIdException(String id, Exception cause) {
    super("Habitat doesnt exist: " + id, cause);
  }
}

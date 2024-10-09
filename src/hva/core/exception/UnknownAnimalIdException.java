package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an invalid ID entry.
 */
public class UnknownAnimalIdException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param message the error message
   **/
  public UnknownAnimalIdException(String id) {
    super("Animal doesnt exist: " + id);
  }

    /**
   * @param message the error message
   * @param cause The cause of the exception
   **/
  public UnknownAnimalIdException(String id, Exception cause) {
    super("Animal doesnt exist: " + id, cause);
  }
}

package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an invalid ID entry.
 */
public class UnknownSpeciesIdException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param message the error message
   **/
  public UnknownSpeciesIdException(String id) {
    super("Species doesnt exist: " + id);
  }

    /**
   * @param message the error message
   * @param cause The cause of the exception
   **/
  public UnknownSpeciesIdException(String id, Exception cause) {
    super("Species doesnt exist: " + id, cause);
  }
}

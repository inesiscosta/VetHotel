package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an invalid ID entry.
 */
public class UnknownIdException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param message the error message
   **/
  public UnknownIdException(String id) {
    super("Id not found:" + id);
  }

    /**
   * @param message the error message
   * @param cause The cause of the exception
   **/
  public UnknownIdException(String id, Exception cause) {
    super("Id not found:" + id, cause);
  }
}

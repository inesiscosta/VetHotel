package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an invalid ID entry.
 */
public class UnknownResponsibilityException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param message the error message
   **/
  public UnknownResponsibilityException(String id) {
    super("Responsibility doesnt exist:" + id);
  }

    /**
   * @param message the error message
   * @param cause The cause of the exception
   **/
  public UnknownResponsibilityException(String id, Exception cause) {
    super("Responsibility doesnt exist:" + id, cause);
  }
}

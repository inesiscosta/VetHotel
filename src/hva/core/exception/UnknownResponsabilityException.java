package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an invalid ID entry.
 */
public class UnknownResponsabilityException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /**
   * @param message the error message
   **/
  public UnknownResponsabilityException(String id) {
    super("Responsability id not found:" + id);
  }

    /**
   * @param message the error message
   * @param cause The cause of the exception
   **/
  public UnknownResponsabilityException(String id, Exception cause) {
    super("Responsability id not found:" + id, cause);
  }
}

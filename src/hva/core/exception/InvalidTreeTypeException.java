package hva.core.exception;

import java.io.Serial;

/**
  * Class for representing an invalid tree type exception.
 **/
public class InvalidTreeTypeException extends Exception {

  @Serial
  private static final long serialVersionUID = 202410232251L;
  
  /**
   * @param message The error message
   **/
  public InvalidTreeTypeException(String type) {
    super("Invalid Tree type: " + type);
  }
}

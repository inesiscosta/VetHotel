package hva.core.exception;

/**
  * Class for representing an invalid tree type exception.
 **/
public class InvalidTreeTypeException extends Exception {
  
  /**
   * @param message The error message
   **/
  public InvalidTreeTypeException(String type) {
    super("Invalid Tree type: " + type);
  }
}

package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an Duplicated Id entry.
 */
public class DuplicateSpeciesNameException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
    /**
   * @param message the name that already exists
   **/
  public DuplicateSpeciesNameException(String id) {
    super("Duplicated Species name: " + id);
  }

  /**
   * @param message the name that already exists
   * @param cause exception that triggered this one
   **/
  public DuplicateSpeciesNameException(String name, Exception cause) {
    super("Duplicated Species name: " + name, cause);
  }
}

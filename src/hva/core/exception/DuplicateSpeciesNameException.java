package hva.core.exception;

/**
 * Class for representing an exception for a duplicate species name.
 */
public class DuplicateSpeciesNameException extends Exception {

  private static final String ERROR_MESSAGE = "Duplicated Species name: ";
  private final String _name;
  
  /**
   * Constructor for the exception.
   * 
   * @param name the name that already exists
   **/
  public DuplicateSpeciesNameException(String name) {
    super(ERROR_MESSAGE + name);
    this._name = name;
  }

  /**
   * Alternative constructor for the exception.
   * 
   * @param id the name that already exists
   * @param cause exception that triggered this one
   **/
  public DuplicateSpeciesNameException(String name, Exception cause) {
    super(ERROR_MESSAGE + name, cause);
    this._name = name;
  }

  /**
   * Gets the duplicated species name.
   * 
   * @return the duplicated species name
   */
  public String name() {
    return _name;
  }
}

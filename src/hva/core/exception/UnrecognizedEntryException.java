package hva.core.exception;

import java.io.Serial;

/**
 * Thrown when the application processes an entry of the import file with an
 * unknown format.
 **/
public class UnrecognizedEntryException extends Exception {

  @Serial
  private static final long serialVersionUID = 202410232301L;
  
  /** Unrecognized entry specification. */
  private final String _entrySpecification;
  
  /**
   * Constructor for the exception.
   * @param entrySpecification the import file entry with an unknown format§
   **/
  public UnrecognizedEntryException(String entrySpecification) {
    _entrySpecification = entrySpecification;
  }
  
  /**
   * Alternative constructor for the exception.
   * 
   * @param entrySpecification the import file entry with an unknown format§
   * @param cause the cause
   **/
  public UnrecognizedEntryException(String entrySpecification, Exception cause) {
    super(cause);
    _entrySpecification = entrySpecification;
  }
  
  /**
   * Get the bad entry specification.
   * 
   * @return the bad entry specification.
   **/
  public String getEntrySpecification() {
    return _entrySpecification;
  }
}

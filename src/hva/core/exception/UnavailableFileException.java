package hva.core.exception;

import java.io.Serial;

/**
 * Represents an error occurred during the serialization/desserialization
 * process of the apllication's state:
 *  - The specified file does not exist
 *  - There is an error while processing this file using the serialization mechanism
 *    of Java.
 */
public class UnavailableFileException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  /** The requested filename. */
  private String _filename;
  
  /**
   * @param filename the filename the previously serialized state or where
   *        the current state of the system should be serialized
   **/
  public UnavailableFileException(String filename) {
    super("Erro a processar ficheiro " + filename);
    _filename = filename;
  }
  
  /**
   * @return the requested filename
   **/
  public String getFilename() {
    return _filename;
  }
}

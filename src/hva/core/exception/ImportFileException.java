package hva.core.exception;

import java.io.Serial;

/**
 * Class for representing an problem occuring during the parsing of an import file.
 */
public class ImportFileException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  private static final String ERROR_MESSAGE = "Erro a processar ficheiro de import: ";
  
  /**
   * @param filename name of the import file
   **/
  public ImportFileException(String filename) {
    super(ERROR_MESSAGE + filename);
  }
  
  /**
   * @param filename name of the import file
   * @param cause exception that triggered this one
   **/
  public ImportFileException(String filename, Exception cause) {
    super(ERROR_MESSAGE + filename, cause);
  }
}

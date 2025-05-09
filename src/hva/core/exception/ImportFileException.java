package hva.core.exception;

/**
 * Class for representing an problem occuring during the parsing of an import file.
 */
public class ImportFileException extends Exception {
  
  private static final String ERROR_MESSAGE = "Erro a processar ficheiro de import: ";
  
  /**
   * Constructor for the exception.
   * 
   * @param filename name of the import file
   **/
  public ImportFileException(String filename) {
    super(ERROR_MESSAGE + filename);
  }
  
  /**
   * Alternative constructor for the exception.
   * 
   * @param filename name of the import file
   * @param cause exception that triggered this one
   **/
  public ImportFileException(String filename, Exception cause) {
    super(ERROR_MESSAGE + filename, cause);
  }
}

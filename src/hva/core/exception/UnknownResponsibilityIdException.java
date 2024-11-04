package hva.core.exception;

/**
 * Class for representing an unknown responsibility id exception.
 */
public class UnknownResponsibilityIdException extends UnknownIdException {

  private static final String SPECIFIC_ERROR_MESSAGE = "Reponsibility id";
  private final String _idEmployee;

  /**
   * Constructor for the exception.
   * 
   * @param id the id that doesnt exist
   **/
  public UnknownResponsibilityIdException(String idResp, String idEmployee) {
    super(SPECIFIC_ERROR_MESSAGE + idResp, idResp);
    this._idEmployee = idEmployee;
  }

  /**
   * Alternative constructor for the exception.
   * 
   * @param id the id that doesnt exist
   * @param cause The cause of the exception
   **/
  public UnknownResponsibilityIdException(String idResp, String idEmployee, Exception cause) {
    super(SPECIFIC_ERROR_MESSAGE + idResp, idResp, cause);
    this._idEmployee = idEmployee;
  }

  /**
   * Gets the id of the employee.
   * 
   * @return the id of the employee
   */
  public String idEmployee() {
    return _idEmployee;
  }
}

package hva.core.exception;

/**
 * Class for representing an employee not responsible exception.
 */
public class EmployeeNotResponsibleException extends Exception {

  private static final String ERROR_MESSAGE = "Employee is not responsible for: ";
  private final String _id;
  private final String _idEmployee;
  
  /**
   * Constructor for the exception.
   * 
   * @param id the id of the reponsibility the employee is not responsible for
   **/
  public EmployeeNotResponsibleException(String id, String idEmployee) {
    super(ERROR_MESSAGE + id);
    this._id = id;
    this._idEmployee = idEmployee;
  }

  /**
   * Alternative constructor for the exception.
   * 
   * @param id the id of the reponsibility the employee is not responsible for
   * @param cause The cause of the exception
   **/
  public EmployeeNotResponsibleException(String id, String idEmployee, Exception cause) {
    super(ERROR_MESSAGE + id, cause);
    this._id = id;
    this._idEmployee = idEmployee;
  }
  
  /**
   * Gets the duplicated id.
   * 
   * @return the duplicated id
   */
  public String id() {
    return _id;
  }

  /**
   * Gets the duplicated id.
   * 
   * @return the duplicated id
   */
  public String idEmployee() {
    return _idEmployee;
  }

}

package hva.app.employee;

import hva.core.Hotel;
import hva.core.exception.DuplicateEmployeeIdException;
import hva.core.exception.DuplicateIdException;
import hva.core.exception.InvalidEmployeeTypeException;
import hva.app.exception.DuplicateEmployeeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Adds a new employee to this zoo hotel.
 **/
class DoRegisterEmployee extends Command<Hotel> {

  DoRegisterEmployee(Hotel receiver) {
    super(Label.REGISTER_EMPLOYEE, receiver);
    addStringField("id", Prompt.employeeKey());
    addStringField("name", Prompt.employeeName());
    addOptionField("type", Prompt.employeeType(), "TRT", "VET");
  }
  
  @Override
  protected void execute() throws CommandException {
    var id = stringField("id");
    var name = stringField("name");
    var type = stringField("type");

    try {
      _receiver.registerEmployee(id, name, type);
    } catch (DuplicateEmployeeIdException e) {
      throw new DuplicateEmployeeKeyException(id);
    } catch (InvalidEmployeeTypeException e) {
    }
  }
}

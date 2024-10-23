package hva.app.employee;

import hva.core.Hotel;
import hva.core.exception.UnknownEmployeeIdException;
import hva.app.exception.UnknownEmployeeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show the satisfaction of a given employee.
 **/
class DoShowSatisfactionOfEmployee extends Command<Hotel> {

  DoShowSatisfactionOfEmployee(Hotel receiver) {
    super(Label.SHOW_SATISFACTION_OF_EMPLOYEE, receiver);
    addStringField("id", Prompt.employeeKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    var id = stringField("id");
    try {
      _display.popup((int) Math.round(_receiver.calculateEmployeeSatisfaction(id)));
    } catch (UnknownEmployeeIdException e) {
      throw new UnknownEmployeeKeyException(id);
    }
  }
}

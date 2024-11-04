package hva.app.employee;

import hva.core.exception.EmployeeNotResponsibleException;
import hva.core.exception.UnknownEmployeeIdException;
import hva.core.exception.UnknownResponsibilityIdException;
import hva.core.Hotel;
import hva.app.exception.NoResponsibilityException;
import hva.app.exception.UnknownEmployeeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Remove a given responsability from a given employee of this zoo hotel.
 **/
class DoRemoveResponsibility extends Command<Hotel> {

  DoRemoveResponsibility(Hotel receiver) {
    super(Label.REMOVE_RESPONSABILITY, receiver);
    addStringField("id", Prompt.employeeKey());
    addStringField("responsibility", Prompt.responsibilityKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    var id = stringField("id");
    var responsibility = stringField("responsibility");
    try {
      _receiver.removeResponsibility(id, responsibility);
    } catch (UnknownResponsibilityIdException e) {
      throw new NoResponsibilityException(e.idEmployee(), e.id());
    } catch (EmployeeNotResponsibleException e) {
      throw new NoResponsibilityException(e.idEmployee(), e.id());
    } catch (UnknownEmployeeIdException e) {
      throw new UnknownEmployeeKeyException(e.id());
    }
  }
}

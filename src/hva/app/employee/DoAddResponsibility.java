package hva.app.employee;

import hva.core.exception.UnknownEmployeeIdException;
import hva.core.exception.UnknownResponsibilityIdException;
import hva.core.Hotel;
import hva.app.exception.NoResponsibilityException;
import hva.app.exception.UnknownEmployeeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Add a new responsability to an employee: species to veterinarians and 
 * habitats to zookeepers.
 **/
class DoAddResponsibility extends Command<Hotel> {

  DoAddResponsibility(Hotel receiver) {
    super(Label.ADD_RESPONSABILITY, receiver);
    addStringField("idEmployee", Prompt.employeeKey());
    addStringField("idResponsibility", Prompt.responsibilityKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    var idEmployee = stringField("idEmployee");
    var idResponsibility = stringField("idResponsibility");
    try {
      _receiver.addResponsibility(idEmployee, idResponsibility);
    } catch (UnknownEmployeeIdException e) {
      throw new UnknownEmployeeKeyException(e.id());
    } catch (UnknownResponsibilityIdException e) {
      throw new NoResponsibilityException(idEmployee, e.id()); //FIXME Inês didnt like how this is made maybe a better way store in this exception both the resp id and employee id Maybe??
    }
  }
}

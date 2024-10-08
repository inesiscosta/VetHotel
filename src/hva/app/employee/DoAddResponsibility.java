package hva.app.employee;

import hva.core.Hotel;
import hva.core.exception.UnknownIdException;
import hva.app.exception.UnknownEmployeeKeyException;
import hva.app.exception.NoResponsibilityException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Add a new responsability to an employee: species to veterinarians and 
 * habitats to zookeepers.
 **/
class DoAddResponsibility extends Command<Hotel> {

  DoAddResponsibility(Hotel receiver) {
    super(Label.ADD_RESPONSABILITY, receiver);
    addStringField("id", Prompt.employeeKey());
    addStringField("responsibility", Prompt.responsibilityKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    var id = stringField("id");
    var responsibility = stringField("responsibility");

    try {
      _receiver.addResponsibility(id, responsibility);
    } catch (UnknownIdException e) {
      if(e.getMessage().contains("Habitat") || e.getMessage().contains("Species")) {
        throw new NoResponsibilityException(id, responsibility);
      } else {
        throw new UnknownEmployeeKeyException(id);
      }
    }
  }
}

package hva.app.employee;

import hva.core.Hotel;
import hva.core.exception.UnknownIdException;
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
    } catch (UnknownIdException e) {
      if(e.getMessage().contains("Habitat") || e.getMessage().contains("Species")) {
        throw new NoResponsibilityException(id, responsibility);
      } else {
        throw new UnknownEmployeeKeyException(id);
      }
    }
  }
}

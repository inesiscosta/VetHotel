package hva.app.habitat;

import hva.core.Hotel;
import hva.core.exception.DuplicateHabitatIdException;
import hva.app.exception.DuplicateHabitatKeyException;
// import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Add a new habitat to this zoo hotel.
 **/
class DoRegisterHabitat extends Command<Hotel> {

  DoRegisterHabitat(Hotel receiver) {
    super(Label.REGISTER_HABITAT, receiver);
    addStringField("id", Prompt.habitatKey());
    addStringField("name", Prompt.habitatName());
    addIntegerField("area", Prompt.habitatArea());
  }
  
  @Override
  protected void execute() throws CommandException {
    var id = stringField("id");
    var name = stringField("name");
    var area = integerField("area");

    try {
      _receiver.registerHabitat(id, name, area);
    } catch (DuplicateHabitatIdException e) {
      throw new DuplicateHabitatKeyException(id);
    }
  }
}
package hva.app.habitat;

import hva.core.Hotel;
import hva.core.exception.UnknownHabitatIdException;
import hva.app.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Change the area of a given habitat.
 **/
class DoChangeHabitatArea extends Command<Hotel> {

  DoChangeHabitatArea(Hotel receiver) {
    super(Label.CHANGE_HABITAT_AREA, receiver);
    addStringField("id", Prompt.habitatKey());
    addIntegerField("area", Prompt.habitatArea());
  }
  
  @Override
  protected void execute() throws CommandException {
    var id = stringField("id");
    var area = integerField("area");
    try {
      _receiver.changeHabitatArea(id, area);
    } catch (UnknownHabitatIdException e) {
      throw new UnknownHabitatKeyException(id);
    }
  }
}

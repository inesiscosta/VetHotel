package hva.app.habitat;

import hva.core.Hotel;
import hva.core.exception.UnknownHabitatIdException;
import hva.app.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show all trees in a given habitat.
 **/
class DoShowAllTreesInHabitat extends Command<Hotel> {

  DoShowAllTreesInHabitat(Hotel receiver) {
    super(Label.SHOW_TREES_IN_HABITAT, receiver);
    addStringField("id", Prompt.habitatKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    var id = stringField("id");
    try {
      _display.popup(_receiver.identifyHabitat(id).listTrees(_receiver.currentSeason())); //TODO trees toString cannot include season
    } catch (UnknownHabitatIdException e) {
      throw new UnknownHabitatKeyException(id);
    }
  }
}

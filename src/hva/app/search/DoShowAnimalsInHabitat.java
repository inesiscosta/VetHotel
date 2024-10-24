package hva.app.search;

import hva.core.exception.UnknownHabitatIdException;
import hva.core.Hotel;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.habitat.Prompt;

import java.util.stream.Collectors;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show all animals of a given habitat.
 **/
class DoShowAnimalsInHabitat extends Command<Hotel> {

  DoShowAnimalsInHabitat(Hotel receiver) {
    super(Label.ANIMALS_IN_HABITAT, receiver);
    addStringField("habitat", Prompt.habitatKey());
  }

  @Override
  protected void execute() throws CommandException {
    var idHabitat = stringField("habitat");
    try {
      _display.popup( _receiver.listAnimalsInHabitat(idHabitat).stream()
      .sorted().collect(Collectors.toList()));
    } catch (UnknownHabitatIdException e) {
      throw new UnknownHabitatKeyException(idHabitat);
    }
  }
}

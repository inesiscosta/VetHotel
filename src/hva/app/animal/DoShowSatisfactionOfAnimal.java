package hva.app.animal;

import hva.core.Hotel;
import hva.core.exception.UnknownAnimalIdException;
import hva.app.exception.UnknownAnimalKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Shows the satisfaction of a given animal.
 */
class DoShowSatisfactionOfAnimal extends Command<Hotel> {

  DoShowSatisfactionOfAnimal(Hotel receiver) {
    super(Label.SHOW_SATISFACTION_OF_ANIMAL, receiver);
    addStringField("id", Prompt.animalKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    var id = stringField("id");
    try {
      _display.popup((int) Math.round(_receiver.identifyAnimal(id).calculateSatisfaction()));
    } catch (UnknownAnimalIdException e) {
      throw new UnknownAnimalKeyException(id);
    }
  }
}

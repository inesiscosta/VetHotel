package hva.app.search;

import hva.core.exception.UnknownAnimalIdException;
import hva.core.Hotel;
import hva.app.exception.UnknownAnimalKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show all medical acts applied to a given animal.
 **/
class DoShowMedicalActsOnAnimal extends Command<Hotel> {

  DoShowMedicalActsOnAnimal(Hotel receiver) {
    super(Label.MEDICAL_ACTS_ON_ANIMAL, receiver);
    addStringField("id", hva.app.animal.Prompt.animalKey());
  }

  @Override
  protected void execute() throws CommandException {
    var id = stringField("id");
    try {
      _display.popup(_receiver.listAnimalVaccinationHistory(id));
    } catch (UnknownAnimalIdException e) {
      throw new UnknownAnimalKeyException(id);
    }
  }
}

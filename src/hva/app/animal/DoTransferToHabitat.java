package hva.app.animal;

import hva.core.Hotel;
import hva.core.exception.UnknownIdException;
import hva.app.exception.UnknownAnimalKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Transfers a given animal to a new habitat of this zoo hotel.
 */
class DoTransferToHabitat extends Command<Hotel> {

  DoTransferToHabitat(Hotel hotel) {
    super(Label.TRANSFER_ANIMAL_TO_HABITAT, hotel);
    addStringField("id", Prompt.animalKey());
    addStringField("habitat", Prompt.habitatKey());
  }
  
  // No error if tries to transfer to the habitat that its already in. Should it?
  @Override
  protected final void execute() throws CommandException {
    var id = stringField("id");
    var idHabitat = stringField("habitat");

    try {
      var animal = _receiver.identifyAnimal(id);
      try {
        var habitat = _receiver.identifyHabitat(idHabitat);
        animal.changeHabitat(habitat);
      } catch (UnknownIdException e) {
        throw new UnknownHabitatKeyException(idHabitat);
      }
    } catch (UnknownIdException e) {
      throw new UnknownAnimalKeyException(id);
    }
  }
}

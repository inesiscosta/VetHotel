package hva.app.animal;

import hva.core.Hotel;
import hva.core.exception.UnknownAnimalIdException;
import hva.core.exception.UnknownHabitatIdException;
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
    addStringField("idAnimal", Prompt.animalKey());
    addStringField("idHabitat", hva.app.habitat.Prompt.habitatKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    var idAnimal = stringField("idAnimal");
    var idHabitat = stringField("idHabitat");
    try {
      var animal = _receiver.identifyAnimal(idAnimal);
      var habitat = _receiver.identifyHabitat(idHabitat);
      animal.changeHabitat(habitat);
    } catch (UnknownAnimalIdException e) {
      throw new UnknownAnimalKeyException(idAnimal);
    } catch (UnknownHabitatIdException e) {
      throw new UnknownHabitatKeyException(idHabitat);
    }
  }
}

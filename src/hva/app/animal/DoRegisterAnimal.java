package hva.app.animal;

import hva.core.Hotel;
import hva.core.exception.UnknownIdException;
import hva.core.exception.DuplicateIdException;
import hva.app.exception.DuplicateAnimalKeyException;
import hva.app.exception.UnknownHabitatKeyException;
// import pt.tecnico.uilib.forms.Form; We don't use this import but I don't know if we should.
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Register a new animal in this zoo hotel.
 */
class DoRegisterAnimal extends Command<Hotel> {

  DoRegisterAnimal(Hotel receiver) {
    super(Label.REGISTER_ANIMAL, receiver);
    addStringField("id", Prompt.animalKey());
    addStringField("name", Prompt.animalName());
    addStringField("species", Prompt.speciesKey());
    addStringField("habitat", Prompt.habitatKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    var id = stringField("id");
    var name = stringField("name");
    var species = stringField("species");
    var habitat = stringField("habitat");

    try {
      _receiver.registerAnimal(id, name, species, habitat);
    } catch (DuplicateIdException e) {
      throw new DuplicateAnimalKeyException(id);
    } catch (UnknownIdException e) {
      throw new UnknownHabitatKeyException(habitat);
    }
  }
}

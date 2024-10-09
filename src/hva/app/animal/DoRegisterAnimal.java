package hva.app.animal;

import hva.core.Hotel;
import hva.core.exception.UnknownIdException;
import hva.core.exception.DuplicateIdException;

import java.text.Normalizer.Form;

import hva.app.exception.DuplicateAnimalKeyException;
import hva.app.exception.UnknownHabitatKeyException;
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
    addStringField("habitat", hva.app.habitat.Prompt.habitatKey());
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
      if (e.getMessage().contains("Habitat")) {
        throw new UnknownHabitatKeyException(habitat);
      } else if (e.getMessage().contains("Species")) {
        addStringField("speciesName", Prompt.speciesName());
        var speciesName = stringField("speciesName");
        try {
        _receiver.registerSpecies(species, speciesName);
        _receiver.registerAnimal(id, name, species, habitat);
        } catch (UnknownIdException e1) {
          throw new UnknownHabitatKeyException(habitat);
        } catch (DuplicateIdException e2) {
          throw new DuplicateAnimalKeyException(species);
        }
      }
    }
  }
}

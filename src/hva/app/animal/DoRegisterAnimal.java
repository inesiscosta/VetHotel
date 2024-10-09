package hva.app.animal;

import hva.core.Hotel;
import hva.core.exception.UnknownSpeciesIdException;
import hva.core.exception.DuplicateAnimalIdException;
import hva.core.exception.DuplicateIdException;
import hva.core.exception.DuplicateSpeciesIdException;
import hva.core.exception.DuplicateSpeciesNameException;
import hva.core.exception.UnknownHabitatIdException;

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
    } catch (DuplicateAnimalIdException e) {
      throw new DuplicateAnimalKeyException(id);
    } catch (UnknownHabitatIdException e) {
        throw new UnknownHabitatKeyException(habitat);
    } catch (UnknownSpeciesIdException e) {
        addStringField("speciesName", Prompt.speciesName());
        var speciesName = stringField("speciesName");
        try {
        _receiver.registerSpecies(species, speciesName);
        _receiver.registerAnimal(id, name, species, habitat);
        } catch (UnknownHabitatIdException e1) {
          throw new UnknownHabitatKeyException(habitat);
        } catch (DuplicateAnimalIdException e2) {
          throw new DuplicateAnimalKeyException(species);
        } catch (DuplicateSpeciesIdException | DuplicateSpeciesNameException | DuplicateIdException 
        | UnknownSpeciesIdException e3) {
        }
    }
  }
}

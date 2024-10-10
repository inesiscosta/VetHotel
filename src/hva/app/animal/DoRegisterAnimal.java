package hva.app.animal;

import hva.core.Hotel;
import hva.core.exception.DuplicateAnimalIdException;
import hva.core.exception.DuplicateIdException;
import hva.core.exception.DuplicateSpeciesIdException;
import hva.core.exception.DuplicateSpeciesNameException;
import hva.core.exception.UnknownHabitatIdException;
import hva.core.exception.UnknownSpeciesIdException;
import hva.app.exception.DuplicateAnimalKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Register a new animal in this Vet Hotel.
 */
class DoRegisterAnimal extends Command<Hotel> {

  DoRegisterAnimal(Hotel receiver) {
    super(Label.REGISTER_ANIMAL, receiver);
    addStringField("idAnimal", Prompt.animalKey());
    addStringField("name", Prompt.animalName());
    addStringField("idSpecies", Prompt.speciesKey());
    addStringField("idHabitat", hva.app.habitat.Prompt.habitatKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    var idAnimal = stringField("idAnimal");
    var name = stringField("name");
    var idSpecies = stringField("idSpecies");
    var idHabitat = stringField("idHabitat");

    try {
      _receiver.registerAnimal(idAnimal, name, idSpecies, idHabitat);
    } catch (DuplicateAnimalIdException e) {
      throw new DuplicateAnimalKeyException(idAnimal);
    } catch (UnknownHabitatIdException e) {
        throw new UnknownHabitatKeyException(idHabitat);
    } catch (UnknownSpeciesIdException e) {
        addStringField("speciesName", Prompt.speciesName());
        var speciesName = stringField("speciesName");
        try {
        _receiver.registerSpecies(idSpecies, speciesName);
        _receiver.registerAnimal(idAnimal, name, idSpecies, idHabitat);
        } catch (DuplicateAnimalIdException e1) {
          throw new DuplicateAnimalKeyException(idSpecies);
        } catch (UnknownHabitatIdException e1) {
          throw new UnknownHabitatKeyException(idHabitat);
        } catch (DuplicateSpeciesIdException | DuplicateSpeciesNameException
        | DuplicateIdException | UnknownSpeciesIdException e1) {        
        }
    }
  }
}

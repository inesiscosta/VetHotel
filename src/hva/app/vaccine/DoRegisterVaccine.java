package hva.app.vaccine;

import hva.core.Hotel;
import hva.core.exception.DuplicateVaccineIdException;
import hva.core.exception.UnknownSpeciesIdException;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.app.exception.DuplicateVaccineKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Apply a vaccine to a given animal.
 **/
class DoRegisterVaccine extends Command<Hotel> {

  DoRegisterVaccine(Hotel receiver) {
    super(Label.REGISTER_VACCINE, receiver);
    addStringField("vaccine", Prompt.vaccineKey());
    addStringField("name", Prompt.vaccineName());
    addStringField("species", Prompt.listOfSpeciesKeys());
  }

  @Override
  protected final void execute() throws CommandException {
    var vaccine = stringField("vaccine");
    var name = stringField("name");
    var species = stringField("species");
    try {
      //Split the species string into an array of species ids ignoring leading and trailing spaces
      String[] speciesArray = species.split("\\s*,\\s*");
      _receiver.registerVaccine(vaccine, name, speciesArray);
    } catch (DuplicateVaccineIdException e) {
      throw new DuplicateVaccineKeyException(vaccine);
    } catch (UnknownSpeciesIdException e) {
      throw new UnknownSpeciesKeyException(species);
    }
  }
}

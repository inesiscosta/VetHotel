package hva.app.vaccine;

import hva.core.exception.DuplicateVaccineIdException;
import hva.core.exception.UnknownSpeciesIdException;
import hva.core.Hotel;
import hva.app.exception.DuplicateVaccineKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Apply a vaccine to a given animal.
 **/
class DoRegisterVaccine extends Command<Hotel> {

  DoRegisterVaccine(Hotel receiver) {
    super(Label.REGISTER_VACCINE, receiver);
    addStringField("id", Prompt.vaccineKey());
    addStringField("name", Prompt.vaccineName());
    addStringField("species", Prompt.listOfSpeciesKeys());
  }

  @Override
  protected final void execute() throws CommandException {
    var id = stringField("id");
    var name = stringField("name");
    var species = stringField("species");
    /*Split the species string into an array of species ids
    ignoring leading and trailing spaces*/
    String[] speciesArray = species.split("\\s*,\\s*");
    try {
      _receiver.registerVaccine(id, name, speciesArray);
    } catch (DuplicateVaccineIdException e) {
      throw new DuplicateVaccineKeyException(id);
    } catch (UnknownSpeciesIdException e) {
      throw new UnknownSpeciesKeyException(e.getMessage()); // FIXME: should not use getMessage
    }
  }
}

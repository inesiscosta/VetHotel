package hva.app.vaccine;

import hva.core.Hotel;
import hva.core.exception.UnknownIdException;
import hva.app.exception.UnknownAnimalKeyException;
import hva.app.exception.UnknownVaccineKeyException;
import hva.app.exception.UnknownVeterinarianKeyException;
import hva.app.exception.VeterinarianNotAuthorizedException; //FIXME throw exception in core if veterinarian is not authorized
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Vaccinate a given animal with a given vaccine by a given veterinarian.
 **/
class DoVaccinateAnimal extends Command<Hotel> {
  DoVaccinateAnimal(Hotel receiver) {
    super(Label.VACCINATE_ANIMAL, receiver);
    addStringField("vaccine", Prompt.vaccineKey());
    addStringField("veterinarian", Prompt.veterinarianKey());
    addStringField("animal", hva.app.animal.Prompt.animalKey());
  }

  @Override
  protected final void execute() throws CommandException {
    var idVaccine = stringField("vaccine");
    var idVet = stringField("veterinarian");
    var idAnimal = stringField("animal");

    try {
      var veterinarian = _receiver.identifyVet(idVet);
      var vaccine = _receiver.identifyVaccine(idVaccine);
      var animal = _receiver.identifyAnimal(idAnimal);
      _receiver.addVaccinationRecord(veterinarian, animal, vaccine);
    } catch (UnknownIdException e) {
      if (e.getMessage().contains("Vaccine")) {
        throw new UnknownVaccineKeyException(idVaccine);
      } else if (e.getMessage().contains("Veterinarian")) {
        throw new UnknownVeterinarianKeyException(idVet);
      } else if (e.getMessage().contains("Animal")) {
        throw new UnknownAnimalKeyException(idAnimal);
      }
    }
  }
}

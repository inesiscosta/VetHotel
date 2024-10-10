package hva.app.vaccine;

import hva.core.Hotel;
import hva.core.exception.EmployeeNotResponsibleException;
import hva.core.exception.UnknownAnimalIdException;
import hva.core.exception.UnknownEmployeeIdException;
import hva.core.exception.UnknownVaccineIdException;
import hva.app.exception.UnknownAnimalKeyException;
import hva.app.exception.UnknownVaccineKeyException;
import hva.app.exception.UnknownVeterinarianKeyException;
import hva.app.exception.VeterinarianNotAuthorizedException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

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
    boolean vaccineApropriated;
    try {
      var veterinarian = _receiver.identifyVet(idVet);
      var vaccine = _receiver.identifyVaccine(idVaccine);
      var animal = _receiver.identifyAnimal(idAnimal);
      vaccineApropriated =_receiver.addVaccinationRecord(veterinarian, animal, vaccine);
    } catch (UnknownVaccineIdException e) {
        throw new UnknownVaccineKeyException(idVaccine);
    } catch (UnknownEmployeeIdException e) {
        throw new UnknownVeterinarianKeyException(idVet);
    } catch (UnknownAnimalIdException e) { 
        throw new UnknownAnimalKeyException(idAnimal);
    } catch (EmployeeNotResponsibleException e) {
      throw new VeterinarianNotAuthorizedException(idVet,idAnimal);
    }
    if(!vaccineApropriated)
     _display.popup(Message.wrongVaccine(idVaccine, idAnimal));
  }
}

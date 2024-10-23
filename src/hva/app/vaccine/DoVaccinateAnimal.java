package hva.app.vaccine;

import hva.core.exception.EmployeeNotResponsibleException;
import hva.core.exception.UnknownAnimalIdException;
import hva.core.exception.UnknownEmployeeIdException;
import hva.core.exception.UnknownVaccineIdException;
import hva.core.Hotel;
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
    addStringField("idVaccine", Prompt.vaccineKey());
    addStringField("idVet", Prompt.veterinarianKey());
    addStringField("idAnimal", hva.app.animal.Prompt.animalKey());
  }

  @Override
  protected final void execute() throws CommandException {
    var idVaccine = stringField("idVaccine");
    var idVet = stringField("idVet");
    var idAnimal = stringField("idAnimal");
    try {
      if(!(_receiver.addVaccinationRecord(idVaccine, idVet, idAnimal)))
        _display.popup(Message.wrongVaccine(idVaccine, idAnimal));
    } catch (UnknownVaccineIdException e) {
      throw new UnknownVaccineKeyException(idVaccine);
    } catch (UnknownEmployeeIdException e) {
      throw new UnknownVeterinarianKeyException(idVet);
    } catch (UnknownAnimalIdException e) { 
      throw new UnknownAnimalKeyException(idAnimal);
    } catch (EmployeeNotResponsibleException e) {
      throw new VeterinarianNotAuthorizedException(idVet,idAnimal);
    }
  }
}

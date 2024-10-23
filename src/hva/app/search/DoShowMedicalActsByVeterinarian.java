package hva.app.search;

import hva.core.Hotel;
import hva.core.exception.UnknownEmployeeIdException;
import hva.app.employee.Prompt;
import hva.app.exception.UnknownVeterinarianKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show all medical acts of a given veterinarian.
 **/
class DoShowMedicalActsByVeterinarian extends Command<Hotel> {

  DoShowMedicalActsByVeterinarian(Hotel receiver) {
    super(Label.MEDICAL_ACTS_BY_VET, receiver);
    addStringField("id", Prompt.employeeKey());
  }
  
  @Override
  protected void execute() throws CommandException {
    var id = stringField("id");
    try {
      _display.popup(_receiver.listVetVaccinationRecords(id));
    } catch (UnknownEmployeeIdException e) {
      throw new UnknownVeterinarianKeyException(id);
    }
  }
}

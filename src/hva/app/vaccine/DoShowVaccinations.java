package hva.app.vaccine;

import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;

/**
 * Show all applied vacines by all veterinarians of this zoo hotel.
 **/
class DoShowVaccinations extends Command<Hotel> {

  DoShowVaccinations(Hotel receiver) {
    super(Label.SHOW_VACCINATIONS, receiver);
  }
  
  @Override
  protected final void execute() {
    /* By default the records are pre-sorted from core by the order the
    vaccines were applied. App could provide a way to sort by other criteria.*/
    _display.popup(_receiver.listVaccinationRecords());
  }
}

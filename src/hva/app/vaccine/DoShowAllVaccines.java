package hva.app.vaccine;

import java.util.stream.Collectors;

import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;

/**
 * Show all vaccines.
 **/
class DoShowAllVaccines extends Command<Hotel> {

  DoShowAllVaccines(Hotel receiver) {
    super(Label.SHOW_ALL_VACCINES, receiver);
  }

  @Override
  protected final void execute() {
    _display.popup(_receiver.listVaccines().stream().sorted().collect(Collectors.toList()));
  }
}

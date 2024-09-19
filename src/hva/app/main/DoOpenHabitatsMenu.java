package hva.app.main;

import hva.core.HotelManager;
import pt.tecnico.uilib.menus.Command;

/**
 * Command for opening the menu for habitat management.
 **/
class DoOpenHabitatsMenu extends Command<HotelManager> {
  DoOpenHabitatsMenu(HotelManager receiver) {
    super(hva.app.main.Label.MENU_HABITATS, receiver);
  }

  @Override
  protected final void execute() {
    (new hva.app.habitat.Menu(_receiver.getHotel())).open();
  }
}

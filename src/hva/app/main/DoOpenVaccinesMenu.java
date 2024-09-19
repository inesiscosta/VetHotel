package hva.app.main;

import hva.core.HotelManager;
import pt.tecnico.uilib.menus.Command;

/**
 * Command for opening the menu for vaccine management.
 **/
class DoOpenVaccinesMenu extends Command<HotelManager> {
  DoOpenVaccinesMenu(HotelManager receiver) {
    super(hva.app.main.Label.MENU_VACCINES, receiver);
  }

  @Override
  protected final void execute() {
    (new hva.app.vaccine.Menu(_receiver.getHotel())).open();
  }
}

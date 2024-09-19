package hva.app.main;

import hva.core.HotelManager;
import pt.tecnico.uilib.menus.Command;

/**
 * Command for opening the menu for animal management.
 **/
class DoOpenAnimalsMenu extends Command<HotelManager> {
  DoOpenAnimalsMenu(HotelManager receiver) {
    super(hva.app.main.Label.MENU_ANIMALS, receiver);
  }

  @Override
  protected final void execute() {
    (new hva.app.animal.Menu(_receiver.getHotel())).open();
  }
}

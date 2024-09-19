package hva.app.main;

import hva.core.HotelManager;
import pt.tecnico.uilib.menus.Command;

/**
 * Command for opening the menu for employee management.
 **/
class DoOpenEmployeesMenu extends Command<HotelManager> {
  DoOpenEmployeesMenu(HotelManager receiver) {
    super(hva.app.main.Label.MENU_EMPLOYEES, receiver);
  }

  @Override
  protected final void execute() {
    (new hva.app.employee.Menu(_receiver.getHotel())).open();
  }
}

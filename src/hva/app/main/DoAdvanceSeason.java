package hva.app.main;

import hva.core.HotelManager;
import pt.tecnico.uilib.menus.Command;

/**
 * Command for advancing the season of the system.
 **/
class DoAdvanceSeason extends Command<HotelManager> {
  DoAdvanceSeason(HotelManager receiver) {
    super(Label.ADVANCE_SEASON, receiver);
  }

  @Override
  protected final void execute() {
    _display.popup(_receiver.nextSeason());
  }
}

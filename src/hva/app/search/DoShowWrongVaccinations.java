package hva.app.search;

import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show all vaccines applied to animals belonging to an invalid species.
 **/
class DoShowWrongVaccinations extends Command<Hotel> {

  DoShowWrongVaccinations(Hotel receiver) {
    super(Label.WRONG_VACCINATIONS, receiver);
  }

  @Override
  protected void execute() throws CommandException {
    _display.popup(_receiver.listErroneousVaccinations());
  }
}

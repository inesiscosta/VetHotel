package hva.app.main;

import hva.core.HotelManager;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command for creating a new zoo hotel.
 **/
class DoNewFile extends Command<HotelManager> {
  DoNewFile(HotelManager receiver) {
    super(Label.NEW_FILE, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    if(_receiver.getHotel().getUnsavedChanges() &&
    Form.confirm(Prompt.saveBeforeExit()))
      new DoSaveFile(_receiver).execute();
    _receiver.newHotel();
  }
}

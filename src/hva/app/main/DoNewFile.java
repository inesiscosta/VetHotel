package hva.app.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import hva.core.HotelManager;
import hva.core.exception.MissingFileAssociationException;
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
    if(Form.confirm(Prompt.saveBeforeExit())) {
      try {
        _receiver.save();
      } catch (MissingFileAssociationException | IOException e) {
      }
    } else {
      _receiver.newHotel();
    }

  }
}

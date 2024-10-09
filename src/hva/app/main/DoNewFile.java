package hva.app.main;

import java.io.IOException;

import hva.core.HotelManager;
import hva.core.exception.MissingFileAssociationException;
import hva.core.exception.UnavailableFileException;
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
    boolean unsavedChange = false;
    try {
      unsavedChange = _receiver.unsavedChanges(_receiver.getHotel());
    } catch (UnavailableFileException e) {}
    if (!unsavedChange) {
      if(Form.confirm(Prompt.saveBeforeExit())) {
        try {
          _receiver.save();
          _receiver.newHotel();
        } catch (MissingFileAssociationException | IOException e) {
          DoSaveFile saveFile = new DoSaveFile(_receiver);
          saveFile.execute();
          _receiver.newHotel();
        } 
      } else {
        _receiver.newHotel();
      }
    } else {
      _receiver.newHotel();
    }  
  }
}

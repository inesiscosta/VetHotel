package hva.app.main;

import hva.core.exception.MissingFileAssociationException;
import hva.core.HotelManager;
import java.io.IOException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;

/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoSaveFile extends Command<HotelManager> {
  DoSaveFile(HotelManager receiver) {
    super(Label.SAVE_FILE, receiver, r -> r.getHotel() != null);
  }

  @Override
  protected final void execute() {
    try {
      if (!_receiver.isAssociated())
        _receiver.saveAs(Form.requestString(Prompt.newSaveAs()));
      _receiver.save();
    } catch (MissingFileAssociationException | IOException e) {
      e.printStackTrace();
    }
  }
}

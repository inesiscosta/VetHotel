package hva.app.main;

import hva.core.HotelManager;
import hva.core.exception.MissingFileAssociationException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;

import java.io.IOException;
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
      String filename;
      if (!_receiver.isAssociated()) {
          filename = Form.requestString(Prompt.newSaveAs());
          _receiver.saveAs(filename);
      } else {
        _receiver.save();
      }
    } catch (MissingFileAssociationException | IOException e) {
       e.printStackTrace();
    }
  }
}

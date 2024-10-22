package hva.app.main;

import hva.core.HotelManager;
import hva.app.exception.FileOpenFailedException;
import hva.core.exception.UnavailableFileException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command to open a file.
 */
class DoOpenFile extends Command<HotelManager> {
  DoOpenFile(HotelManager receiver) {
    super(Label.OPEN_FILE, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    //FIXME Check with teacher if its ok to create an empty hotel here for code reuse or if we can add a new .java to reuse here.
    new DoNewFile(_receiver).execute();
    try {
      _receiver.load(Form.requestString(Prompt.openFile()));
    } catch (UnavailableFileException e) {
      throw new FileOpenFailedException(e);
    }
  }
}

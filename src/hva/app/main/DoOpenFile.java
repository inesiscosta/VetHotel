package hva.app.main;

import hva.core.HotelManager;
import hva.app.exception.FileOpenFailedException;
//import hva.app.exception.ImportFileException;
import hva.core.exception.UnavailableFileException;

import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Command to open a file.
 */
class DoOpenFile extends Command<HotelManager> {
  DoOpenFile(HotelManager receiver) {
    super(Label.OPEN_FILE, receiver);
    addBooleanField("save", Prompt.saveBeforeExit());
    addStringField("filename", Prompt.openFile());
  }

  @Override
  protected final void execute() throws CommandException {
    var save = booleanField("save");
    String filename = stringField("filename");
    if(save) {
       DoSaveFile saveFile = new DoSaveFile(_receiver);
       saveFile.execute();
    }
    try {
      _receiver.load(filename);
   // } catch (UnavailableFileException | ClassNotFoundException | FileNotFoundException | hva.core.exception.ImportFileException efe) {
      } catch (UnavailableFileException e) {
        throw new FileOpenFailedException(e);
    }
  }
}

package hva.app.main;

import hva.core.HotelManager;

import java.io.IOException;

import hva.app.exception.FileOpenFailedException;
import hva.core.exception.MissingFileAssociationException;
//import hva.app.exception.ImportFileException;
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
    addStringField("filename", Prompt.openFile());
  }

  @Override
  protected final void execute() throws CommandException {
    String filename = stringField("filename");
    if(_receiver.getHotel().getUnsavedChanges()) {
      if (Form.confirm(Prompt.saveBeforeExit())) {
          try {
            _receiver.save();
            _receiver.newHotel();
          } catch (MissingFileAssociationException | IOException e) {
            DoSaveFile saveFile = new DoSaveFile(_receiver);
            saveFile.execute();
            try {
              _receiver.load(filename);
            } catch (UnavailableFileException e1) {
                throw new FileOpenFailedException(e1);
            }
          } 
      } else {
        try {
          _receiver.load(filename);
        } catch (UnavailableFileException e) {
            throw new FileOpenFailedException(e);
        }
      }
    } else {
      try {
        _receiver.load(filename);
      } catch (UnavailableFileException e) {
          throw new FileOpenFailedException(e);
      }
    }
  }
}

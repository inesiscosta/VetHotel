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
  }

  @Override
  protected final void execute() throws CommandException {
    //try {
      //_reciever.load(stringField("filename"));
    //} catch (UnavailableFileException | ClassNotFoundException | FileNotFoundException | hva.core.exception.ImportFileException efe) {
    //  throw new FileOpenFailedException(efe);
    //}
  }
}

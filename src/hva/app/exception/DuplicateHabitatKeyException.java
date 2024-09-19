package hva.app.exception;

import pt.tecnico.uilib.menus.CommandException;

import java.io.Serial;

public class DuplicateHabitatKeyException extends CommandException {
  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  public DuplicateHabitatKeyException(String id) {
    super(Message.duplicateHabitatKey(id));
  }
}

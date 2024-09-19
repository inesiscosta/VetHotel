package hva.app.exception;

import pt.tecnico.uilib.menus.CommandException;

import java.io.Serial;

public class UnknownHabitatKeyException extends CommandException {
  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  public UnknownHabitatKeyException(String id) {
    super(Message.unknownHabitatKey(id));
  }
}

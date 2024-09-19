package hva.app.exception;

import pt.tecnico.uilib.menus.CommandException;

import java.io.Serial;

public class UnknownTreeKeyException extends CommandException {
  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  public UnknownTreeKeyException(String id) {
    super(Message.unknownTreeKey(id));
  }
}

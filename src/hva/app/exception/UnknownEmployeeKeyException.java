package hva.app.exception;

import pt.tecnico.uilib.menus.CommandException;

import java.io.Serial;

public class UnknownEmployeeKeyException extends CommandException {
  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  public UnknownEmployeeKeyException(String id) {
    super(Message.unknownEmployeeKey(id));
  }
}

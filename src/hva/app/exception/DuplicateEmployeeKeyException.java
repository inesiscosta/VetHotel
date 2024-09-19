package hva.app.exception;

import pt.tecnico.uilib.menus.CommandException;

import java.io.Serial;

public class DuplicateEmployeeKeyException extends CommandException {
  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  public DuplicateEmployeeKeyException(String id) {
    super(Message.duplicateEmployeeKey(id));
  }
}

package hva.app.exception;

import pt.tecnico.uilib.menus.CommandException;

import java.io.Serial;

public class DuplicateAnimalKeyException extends CommandException {
  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  public DuplicateAnimalKeyException(String id) {
    super(Message.duplicateAnimalKey(id));
  }
}

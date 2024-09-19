package hva.app.exception;

import pt.tecnico.uilib.menus.CommandException;

import java.io.Serial;

public class UnknownAnimalKeyException extends CommandException {
  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  public UnknownAnimalKeyException(String id) {
    super(Message.unknownAnimalKey(id));
  }
}

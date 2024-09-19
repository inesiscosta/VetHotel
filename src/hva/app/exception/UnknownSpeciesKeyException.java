package hva.app.exception;

import pt.tecnico.uilib.menus.CommandException;

import java.io.Serial;

public class UnknownSpeciesKeyException extends CommandException {
  @Serial
  private static final long serialVersionUID = 202407081733L;

  public UnknownSpeciesKeyException(String key) {
    super(Message.unknownSpeciesKey(key));
  }
}

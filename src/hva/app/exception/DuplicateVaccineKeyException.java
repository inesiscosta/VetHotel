package hva.app.exception;

import pt.tecnico.uilib.menus.CommandException;

import java.io.Serial;

public class DuplicateVaccineKeyException extends CommandException {
  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  public DuplicateVaccineKeyException(String id) {
    super(Message.duplicateVaccineKey(id));
  }
}

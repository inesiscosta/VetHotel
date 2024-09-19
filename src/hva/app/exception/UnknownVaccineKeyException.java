package hva.app.exception;

import pt.tecnico.uilib.menus.CommandException;

import java.io.Serial;

public class UnknownVaccineKeyException extends CommandException {
  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  public UnknownVaccineKeyException(String id) {
    super(Message.unknownVaccineKey(id));
  }
}

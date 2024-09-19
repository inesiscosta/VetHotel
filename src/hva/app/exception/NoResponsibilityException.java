package hva.app.exception;

import pt.tecnico.uilib.menus.CommandException;

import java.io.Serial;

public class NoResponsibilityException extends CommandException {
  @Serial
  private static final long serialVersionUID = 202407081733L;

  public NoResponsibilityException(String employeeKey, String responsibilityKey) {
    super(Message.noResponsibility(employeeKey, responsibilityKey));
  }
}

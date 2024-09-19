package hva.app.exception;

import pt.tecnico.uilib.menus.CommandException;

import java.io.Serial;

public class VeterinarianNotAuthorizedException extends CommandException {
  @Serial
  private static final long serialVersionUID = 202407081733L;
  
  public VeterinarianNotAuthorizedException(String idVet, String idSpecies) {
    super(Message.notAuthorized(idVet, idSpecies));
  }
}

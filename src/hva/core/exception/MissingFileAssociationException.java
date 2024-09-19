package hva.core.exception;

import java.io.Serial;

/**
 * Represents the case where someone tries to save the current state of the application
 * into a file and the application is not associated with a file.
 **/ 
public class MissingFileAssociationException extends Exception {

  @Serial
  private static final long serialVersionUID = 202407081733L;
}

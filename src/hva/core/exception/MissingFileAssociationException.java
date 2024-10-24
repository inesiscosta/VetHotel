package hva.core.exception;

/**
 * Represents the case where someone tries to save the current state of the application
 * into a file and the application is not associated with a file.
 **/ 
public class MissingFileAssociationException extends Exception {
}

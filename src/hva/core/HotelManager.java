package hva.core;

import hva.core.exception.ImportFileException;
import hva.core.exception.MissingFileAssociationException;
import hva.core.exception.UnavailableFileException;
import hva.core.exception.UnrecognizedEntryException;
import hva.core.observers.HotelObserver;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Class representing the manager of this application. It manages the current
 * Vet hotel.
 **/
public class HotelManager implements HotelObserver {
  
  private Hotel _hotel;

  public HotelManager() {
    _hotel = new Hotel();
    _hotel.addHotelObserver(this);
  }

  /**
   * Checks if the hotel is associated with a file
   * 
   * @return true if the hotel is associated with a file, false otherwise
   */
  public boolean isAssociated() {
    return (_hotel.getAssociatedFilename() != null);
  }

  /**
   * Returns the zoo hotel managed by this instance.
   *
   * @return the current zoo hotel
   **/
  public final Hotel getHotel() {
    return _hotel;
  }

  /**
   * Creates a new hotel with a anonymous file associated
   */
  public void newHotel() {
    _hotel = new Hotel();
    _hotel.addHotelObserver(this);
  }

  /**
   * Updates the unsavedChanges
   * 
   * @param state the unsavedChanges current state of the hotel
   */
  @Override
  public void update(boolean state) {
    _hotel.unsavedChanges(state);
  } 

  /**
   * Advacances the season in the hotels
   * 
   * @return the next season
   */
  public int nextSeason() {
    return _hotel.nextSeason();
  }

  /**
   * Saves the serialized application's state into the file
   * associated to the current network.
   *
   * @throws MissingFileAssociationException if the current network
   * doesnt have a file
   * @throws FileNotFoundException if the file cannot be created or opened
   * @throws IOException if there is some error while serializing
   * the state of the network to disk
   **/
  public void save() throws MissingFileAssociationException,
  FileNotFoundException, IOException {
    if (!isAssociated())
      throw new MissingFileAssociationException();
    saveAs(_hotel.getAssociatedFilename());
  }
  
  /**
   * Saves the serialized application's state into the specified file. 
   * The current network is associated to this file.
   *
   * @param filename the name of the file
   * @throws FileNotFoundException if for some reason the file 
   * cannot be created or opened
   * @throws IOException if there is some error while serializing
   * the state of the network to disk
   **/
  public void saveAs(String filename) 
  throws FileNotFoundException , IOException {
    _hotel.setAssociatedFilename(filename);
    ObjectOutputStream exportedHotel = 
    new ObjectOutputStream(new FileOutputStream(filename));
    update(false);
    exportedHotel.writeObject(_hotel);
    exportedHotel.close();
  }
  
  /**
   * Loads the serialized application's state from the specified file.
   * 
   * @param filename name of the file containing the serialized 
   * application's state to load.
   * @throws UnavailableFileException if the specified file does
   * not exist or there is an error while processing this file.
   **/
  public void load(String filename) throws UnavailableFileException {
    try (ObjectInputStream importedHotel =
    new ObjectInputStream(new FileInputStream(filename))) {
      _hotel = (Hotel) importedHotel.readObject();
      _hotel.setAssociatedFilename(filename);
    } catch (IOException | ClassNotFoundException e) {
      throw new UnavailableFileException(filename);
    }
  }
  
  /**
   * Read text input file and initializes the current vet hotel (which should
   * be empty) with the domain entitiesi representeed in the import file.
   *
   * @param filename name of the text input file
   * @throws ImportFileException if some error happens during the 
   * processing of the import file.
   **/
  public void importFile(String filename) throws ImportFileException {
    try {
      _hotel.importFile(filename);
    } catch (IOException | UnrecognizedEntryException e) {
      throw new ImportFileException(filename, e);
    }
  } 
}

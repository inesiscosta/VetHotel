package hva.core;

import hva.core.exception.ImportFileException;
import hva.core.exception.MissingFileAssociationException;
import hva.core.exception.UnavailableFileException;
import hva.core.exception.UnrecognizedEntryException;
import hva.core.modificationObserver.HotelObserver;

import java.io.*;

/**
 * Class representing the manager of this application. It manages the current
 * Vet hotel.
 **/
public class HotelManager implements HotelObserver {
  // The current zoo hotel 
  private Hotel _hotel;
  public HotelManager() {
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
   * Checks if the hotel is associated with a file
   */
  public boolean isAssociated() {
    if (_hotel.getAssociatedFilename().equals(null))
      return false;
    return true;
  }

  /**
   * Advacances the season in the hotels
   */
  public int nextSeason() {
    return _hotel.nextSeason();
  }

  /**
   * Creates a new hotel with a anonymous file associated
   */
  public void newHotel() {
    _hotel = new Hotel();
    _hotel.addHotelObserver(this);
  }

  /**
   * Saves the serialized application's state into the file
   * associated to the current network.
   *
   * @throws FileNotFoundException if the file cannot be created or opened
   * @throws MissingFileAssociationException if the current network
   * doesnt have a file
   * @throws IOException if there is some error while serializing
   * the state of the network to disk
   **/
  public void save() throws FileNotFoundException,
  MissingFileAssociationException, IOException {
    if(isAssociated()) {
      saveAs(_hotel.getAssociatedFilename());
    } else {
      throw new MissingFileAssociationException();
    }
  }
  
  /**
   * Saves the serialized application's state into the specified file. 
   * The current network is associated to this file.
   *
   * @param filename the name of the file
   * @throws FileNotFoundException if for some reason the file 
   * cannot be created or opened
   * @throws MissingFileAssociationException if the current network
   * does not have a file
   * @throws IOException if there is some error while serializing
   * the state of the network to disk
   **/
  public void saveAs(String filename) throws FileNotFoundException,
  MissingFileAssociationException, IOException {
    if(!isAssociated() && !_hotel.getAssociatedFilename().equals(filename))
      throw new MissingFileAssociationException();
    _hotel.setAssociatedFilename(filename);
    FileOutputStream file = new FileOutputStream(filename);
    ObjectOutputStream exportedHotel = new ObjectOutputStream(file);
    update(false);
    exportedHotel.writeObject(_hotel);
    exportedHotel.close(); 
  }
  
  /**
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
  
  /**
   * Returns the zoo hotel managed by this instance.
   *
   * @return the current zoo hotel
   **/
  public final Hotel getHotel() {
    return _hotel;
  }
}

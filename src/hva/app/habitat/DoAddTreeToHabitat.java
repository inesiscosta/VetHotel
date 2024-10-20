package hva.app.habitat;

import hva.core.Hotel;
import hva.core.exception.DuplicateTreeIdException;
import hva.core.exception.InvalidTreeTypeException;
import hva.core.exception.UnknownHabitatIdException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.DuplicateTreeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Add a new tree to a given habitat of the current zoo hotel.
 **/
class DoAddTreeToHabitat extends Command<Hotel> {

  DoAddTreeToHabitat(Hotel receiver) {
    super(Label.ADD_TREE_TO_HABITAT, receiver);
    addStringField("habitat", Prompt.habitatKey());
    addStringField("id", Prompt.treeKey());
    addStringField("name", Prompt.treeName());
    addIntegerField("age", Prompt.treeAge());
    addIntegerField("difficulty", Prompt.treeDifficulty());
    addOptionField("type", Prompt.treeType(), "PERENE", "CADUCA");
  }
  
  @Override
  protected void execute() throws CommandException {
    var habitat = stringField("habitat");
    var id = stringField("id");
    var name = stringField("name");
    var age = integerField("age");
    var difficulty = integerField("difficulty");
    var type = (optionField("type"));

    try {
      _display.popup(_receiver.identifyHabitat(habitat).plantTree(id, name,
      age, difficulty, type, _receiver.currentSeason(), _receiver).toString(
        _receiver.currentSeason()));
    } catch (UnknownHabitatIdException e) {
      throw new UnknownHabitatKeyException(habitat);
    } catch (DuplicateTreeIdException e) {
      throw new DuplicateTreeKeyException(id);
    } catch (InvalidTreeTypeException e) {
      e.printStackTrace();
    }
    //Notfify from app instead of core since plant tree is not called from Hotel
    _receiver.notifyHotelObservers();
  }
}

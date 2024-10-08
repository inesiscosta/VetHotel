package hva.app.habitat;

import hva.core.Hotel;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.DuplicateTreeKeyException;
import hva.core.TreeType;
import hva.core.exception.DuplicateIdException;
import hva.core.exception.InvalidTypeException;
import hva.core.exception.UnknownIdException;
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
    addOptionField("type", Prompt.treeType(), "PER", "CAD");
  }
  
  @Override
  protected void execute() throws CommandException {
    var habitat = stringField("habitat");
    var id = stringField("id");
    var name = stringField("name");
    var age = integerField("age");
    var difficulty = integerField("difficulty");
    var type = TreeType.stringToEnum(optionField("type"));
    

    try {
      _receiver.identifyHabitat(habitat).plantTree(id, name, age, difficulty, type, _receiver.currentSeason(), _receiver);
    } catch (InvalidTypeException | UnknownIdException e) {
      throw new UnknownHabitatKeyException(habitat);
    } catch (DuplicateIdException e) {
      throw new DuplicateTreeKeyException(id);
    }
  }
}

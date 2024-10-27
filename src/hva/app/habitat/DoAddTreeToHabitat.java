package hva.app.habitat;

import hva.core.exception.DuplicateTreeIdException;
import hva.core.exception.InvalidTreeTypeException;
import hva.core.exception.UnknownHabitatIdException;
import hva.core.Hotel;
import hva.app.exception.DuplicateTreeKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Add a new tree to a given habitat of the current zoo hotel.
 **/
class DoAddTreeToHabitat extends Command<Hotel> {

  DoAddTreeToHabitat(Hotel receiver) {
    super(Label.ADD_TREE_TO_HABITAT, receiver);
    addStringField("idHabitat", Prompt.habitatKey());
    addStringField("idTree", Prompt.treeKey());
    addStringField("name", Prompt.treeName());
    addIntegerField("age", Prompt.treeAge());
    addIntegerField("difficulty", Prompt.treeDifficulty());
    addOptionField("type", Prompt.treeType(), "PERENE", "CADUCA");
  }
  
  @Override
  protected void execute() throws CommandException {
    var idHabitat = stringField("idHabitat");
    var idTree = stringField("idTree");
    var name = stringField("name");
    var age = integerField("age");
    var difficulty = integerField("difficulty");
    var type = (optionField("type"));
    try {
      _display.popup(_receiver.addTreeToHabitat(
      idHabitat, idTree, name, age, difficulty, type));
    } catch (UnknownHabitatIdException e) {
      throw new UnknownHabitatKeyException(idHabitat);
    } catch (DuplicateTreeIdException e) {
      throw new DuplicateTreeKeyException(idTree);
    } catch (InvalidTreeTypeException e) {
      // Should never happen since the type is selected from an optionField.
      e.printStackTrace();
    }
  }
}

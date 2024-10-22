package hva.app.habitat;

import hva.core.Hotel;
import hva.core.exception.UnknownHabitatIdException;
import hva.core.exception.UnknownSpeciesIdException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Associate (positive or negatively) a species to a given habitat.
 **/
class DoChangeHabitatInfluence extends Command<Hotel> {

  DoChangeHabitatInfluence(Hotel receiver) {
    super(Label.CHANGE_HABITAT_INFLUENCE, receiver);
    addStringField("idHabitat", Prompt.habitatKey());
    addStringField("idSpecies", hva.app.animal.Prompt.speciesKey());
    addOptionField("influenceString", 
    Prompt.habitatInfluence(),"POS", "NEG", "NEU");
  }
  
  @Override
  protected void execute() throws CommandException {
    var idHabitat = stringField("idHabitat");
    var idSpecies = stringField("idSpecies");
    var influenceString = optionField("influenceString");
    int influence;
    switch (influenceString) { //TODO Revise case switch
      case "POS":
        influence = 20;
        break;
      case "NEG":
        influence = -20;
        break;
      default:
        influence = 0;
    }
    try {
      _receiver.identifyHabitat(idHabitat).changeInfluence(
        _receiver.identifySpecies(idSpecies), influence);
      _receiver.notifyHotelObservers();
    } catch (UnknownHabitatIdException e) {
      throw new UnknownHabitatKeyException(idHabitat);
    } catch (UnknownSpeciesIdException e) {
      throw new UnknownSpeciesKeyException(idSpecies);
    }
  }
}

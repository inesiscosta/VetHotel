package hva.app.habitat;

import hva.core.Hotel;
import hva.core.exception.UnknownIdException;
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
    addOptionField("influnce", Prompt.habitatInfluence(), "POS", "NEG", "NEU");
  }
  
  @Override
  protected void execute() throws CommandException {
    var idHabitat = stringField("idHabitat");
    var idSpecies = stringField("idSpecies");
    var influence = optionField("influence");
    
    try {
      _receiver.changeHabitatInflunece(_receiver.identifyHabitat(idHabitat), _receiver.identifySpecies(idSpecies), influence);
    } catch (UnknownIdException e) {
      if(e.getMessage().contains("Habitat"))
        throw new UnknownHabitatKeyException(idHabitat);
      if(e.getMessage().contains("Species"))
        throw new UnknownSpeciesKeyException(idSpecies);
    }
  }
}

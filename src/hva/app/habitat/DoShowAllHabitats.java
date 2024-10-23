package hva.app.habitat;

import java.util.stream.Collectors;
import java.util.ArrayList;

import hva.core.Hotel;
import hva.core.NamedEntity;
import hva.core.exception.UnknownHabitatIdException;
import pt.tecnico.uilib.menus.Command;

/**
 * Show all habitats of this zoo hotel.
 **/
class DoShowAllHabitats extends Command<Hotel> {

  DoShowAllHabitats(Hotel receiver) {
    super(Label.SHOW_ALL_HABITATS, receiver);
  }
  
  @Override
  protected void execute() {
    var habitatsList = _receiver.listHabitats().stream().sorted().collect(Collectors.toList());
    var displayList = new ArrayList<>();
    for (NamedEntity habitat : habitatsList) {
      displayList.add(habitat);
      try {
        displayList.addAll(_receiver.listAllTreesHabitat(habitat.id()).stream().sorted().collect(Collectors.toList()));
      } catch (UnknownHabitatIdException e) {
        e.printStackTrace(); // This error will never happen
      }
    }
    _display.popup(displayList);
  }
}

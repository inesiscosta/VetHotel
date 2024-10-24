package hva.app.habitat;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import hva.core.Hotel;
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
    var habitatsList = _receiver.listHabitats().stream().sorted();
    var displayList = habitatsList.flatMap(habitat -> {
      try {
        return Stream.concat(Stream.of(habitat), _receiver.listAllTreesHabitat(habitat.id()).stream().sorted());
      } catch (UnknownHabitatIdException e) {
        e.printStackTrace(); // This error will never happen
        return Stream.empty();
      }
    }).collect(Collectors.toList());
    _display.popup(displayList);
  }
}

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
    var habitatsAndTheirTrees = _receiver.listHabitats().stream().sorted()
    .flatMap(habitat -> {
      try {
        return Stream.concat(Stream.of(habitat),
        _receiver.listAllTreesHabitat(habitat.id()).stream().sorted());
      } catch (UnknownHabitatIdException e) {
        return Stream.empty(); // This will never happen.
      }
    }).collect(Collectors.toList());
    _display.popup(habitatsAndTheirTrees);
  }
}

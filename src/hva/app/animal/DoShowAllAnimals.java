package hva.app.animal;

import java.util.stream.Collectors;

import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;

/**
 * Show all animals registered in this Vet Hotel.
 */
class DoShowAllAnimals extends Command<Hotel> {

  DoShowAllAnimals(Hotel receiver) {
    super(Label.SHOW_ALL_ANIMALS, receiver);
  }
  
  @Override
  protected final void execute() {
    _display.popup(_receiver.listAnimals().stream().sorted()
    .collect(Collectors.toList()));
  }
}

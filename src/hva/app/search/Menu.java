package hva.app.search;

public class Menu extends pt.tecnico.uilib.menus.Menu {

  public Menu(hva.core.Hotel receiver) {
    super(Label.TITLE, //
          new DoShowAnimalsInHabitat(receiver),
          new DoShowMedicalActsOnAnimal(receiver),
          new DoShowMedicalActsByVeterinarian(receiver),
          new DoShowWrongVaccinations(receiver)
          );
  }
}

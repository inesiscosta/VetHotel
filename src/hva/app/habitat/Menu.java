package hva.app.habitat;

public class Menu extends pt.tecnico.uilib.menus.Menu {

  public Menu(hva.core.Hotel receiver) {
    super(Label.TITLE, //
          new DoShowAllHabitats(receiver),
          new DoRegisterHabitat(receiver),
          new DoChangeHabitatArea(receiver),
          new DoChangeHabitatInfluence(receiver),
          new DoAddTreeToHabitat(receiver),
          new DoShowAllTreesInHabitat(receiver)
          );
  }
}

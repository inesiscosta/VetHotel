package hva.app.employee;

public class Menu extends pt.tecnico.uilib.menus.Menu {
  public Menu(hva.core.Hotel receiver) {
    super(Label.TITLE, //
          new DoShowAllEmployees(receiver),
          new DoRegisterEmployee(receiver),
          new DoAddResponsibility(receiver),
          new DoRemoveResponsibility(receiver),
          new DoShowSatisfactionOfEmployee(receiver)
          );
  }
}

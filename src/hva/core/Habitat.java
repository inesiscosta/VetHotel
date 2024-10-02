package hva.core;

public class Habitat extends NamedEntity {
    private int _area;

    public Habitat(String idHabitat, String name, int area) {
        super(idHabitat, name);
        _area = area;
    }
    
}

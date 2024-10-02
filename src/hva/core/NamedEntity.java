package hva.core;

public class NamedEntity {
    private final String _id;
    private final String _name;

    public NamedEntity(String name, String id) {
        _name = name;
        _id = id;
    }

    public String name(){
        return _name;
    }

    public String id() {
        return _id;
    }
}

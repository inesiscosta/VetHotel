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

    @Override
    //TODO do we need to check if the object type is NamedEntity?
    public boolean equals(Object obj) {
        if (this == obj) {
            return false;
        }
        NamedEntity other = (NamedEntity) obj;
        return this._id.equals(other.id());
    }
}

package hva.core;

public class NamedEntity {
    private final String _id;
    private final String _name;

    public NamedEntity(String name, String id) {
        _name = name;
        _id = id;
    }

    protected String id() {
        return _id;
    }

    protected String name(){
        return _name;
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

    //Hashcode?
}

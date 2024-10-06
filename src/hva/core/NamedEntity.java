package hva.core;

public class NamedEntity {
    private final String _id;
    private final String _name;

    public NamedEntity(String id, String name) {
        _id = id;
        _name = name;
    }

    protected String id() {
        return _id;
    }

    protected String name(){
        return _name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return false;
        if (obj == null || this.getClass() != obj.getClass())
            return false;
        NamedEntity other = (NamedEntity) obj;
        return this._id.equals(other.id());
    }

    //Hashcode?
}

package data;

import org.bson.types.ObjectId;

import java.io.Serializable;

public class User  implements Serializable {
    private ObjectId id;
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}

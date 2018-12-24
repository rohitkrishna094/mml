package com.rohit.mml.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
public class Role {
    @Id
    private String _id;
    private RoleName name;

    public Role() {
        this._id = ObjectId.get().toString();
    }

    public Role(RoleName name, String _id) {
        this.name = name;
        this._id = _id;
    }

    public Role(RoleName name) {
        this.name = name;
        this._id = ObjectId.get().toString();
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

}

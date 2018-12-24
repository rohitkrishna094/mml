package com.rohit.mml.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rating {

    @Id
    private String _id;

    @JsonProperty("Source")
    private String source;

    @JsonProperty("Value")
    private String value;

    public Rating() {
        this._id = ObjectId.get().toString();
    }

    public Rating(String source, String value) {
        super();
        this._id = ObjectId.get().toString();
        this.source = source;
        this.value = value;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}

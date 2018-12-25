package com.rohit.mml.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ObjectIdSerializer extends JsonSerializer<Object> {
    @Override
    public void serialize(Object value, JsonGenerator jsonGen, SerializerProvider provider) throws IOException {
        // if (value == null && value instanceof List<?> == false && value instanceof Object[] == false)
        // jsonGen.writeString("");
        jsonGen.writeString(value.toString());
    }
}
package com.rohit.mml.util;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StringUtils {

    public static List<String> csvToList(String s) {
        return Arrays.asList(s.split(", "));
    }

    public static String pojoToJsonWithView(Object obj, Class<?> serializationView) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithView(serializationView).writeValueAsString(obj);
    }

    public static String pojoToJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

}

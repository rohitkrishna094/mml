package com.rohit.mml.util;

import java.util.Arrays;
import java.util.List;

public class StringUtils {

    public static List<String> csvToList(String s) {
        return Arrays.asList(s.split(", "));
    }

}

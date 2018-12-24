package com.rohit.mml.jacksonviews;

// https://stackoverflow.com/questions/8179986/jackson-change-jsonignore-dynamically
public class UserViews {
    public static class Public {
    }

    public static class ExtendedPublic extends Public {
    }
}

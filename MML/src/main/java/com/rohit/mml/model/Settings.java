package com.rohit.mml.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Settings {

    private Theme theme = Theme.THEME_LIGHT;

    public Settings() {
    }

    public Settings(Theme theme) {
        this.theme = theme;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().disableHtmlEscaping().setDateFormat("yyyy-MM-dd").create();
        return gson.toJson(this);
    }
}

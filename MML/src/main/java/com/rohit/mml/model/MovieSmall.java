package com.rohit.mml.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Document(collection = "moviesSmall")
public class MovieSmall {

    @Id
    private String id;

    private String url;
    private String imageUrl;

    public MovieSmall() {

    }

    public MovieSmall(String url, String imageUrl) {
        super();
        this.url = url;
        this.imageUrl = imageUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().disableHtmlEscaping().setDateFormat("yyyy-MM-dd").create();
        return gson.toJson(this);
    }

}

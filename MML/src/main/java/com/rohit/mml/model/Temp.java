package com.rohit.mml.model;

public class Temp {

    private String url;
    private String imageUrl;

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

    public Temp(String url, String imageUrl) {
        super();
        this.url = url;
        this.imageUrl = imageUrl;
    }

    // @Override
    // public String toString() {
    // Gson gson = new GsonBuilder().disableHtmlEscaping().setDateFormat("yyyy-MM-dd").create();
    // return gson.toJson(this);
    // }

}

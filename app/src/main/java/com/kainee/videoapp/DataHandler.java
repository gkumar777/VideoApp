package com.kainee.videoapp;

public class DataHandler {

    String title, url;

    public DataHandler(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}

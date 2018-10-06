package com.ashokmachineni.videodt;

public class Jobs {
    private String title;
    private String thumbnail;
    private String url;
    public Jobs(){}

    public Jobs(String title, String thumbnail, String url) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}

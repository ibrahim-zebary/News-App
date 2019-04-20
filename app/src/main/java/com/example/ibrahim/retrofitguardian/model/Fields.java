package com.example.ibrahim.retrofitguardian.model;

import com.google.gson.annotations.SerializedName;

public class Fields {
    /**
     * byline : Rob Smyth
     * thumbnail : https://media.guim.co.uk/393361883477ef9b6af7f2b8167c2f88b7e92e8f/0_109_4928_2958/500.jpg
     */

    @SerializedName("byline")
    private String byline;
    @SerializedName("thumbnail")
    private String thumbnail;

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}

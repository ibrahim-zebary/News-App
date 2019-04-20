package com.example.ibrahim.retrofitguardian.model;

import com.google.gson.annotations.SerializedName;

public class Results {
    /**
     * id : football/live/2018/aug/11/newcastle-united-v-tottenham-hotspur-premier-league-live
     * type : liveblog
     * sectionId : football
     * sectionName : Football
     * webPublicationDate : 2018-08-11T11:32:26Z
     * webTitle : Newcastle United v Tottenham Hotspur: Premier League â€“ live!
     * webUrl : https://www.theguardian.com/football/live/2018/aug/11/newcastle-united-v-tottenham-hotspur-premier-league-live
     * apiUrl : https://content.guardianapis.com/football/live/2018/aug/11/newcastle-united-v-tottenham-hotspur-premier-league-live
     * fields : {"byline":"Rob Smyth","thumbnail":"https://media.guim.co.uk/393361883477ef9b6af7f2b8167c2f88b7e92e8f/0_109_4928_2958/500.jpg"}
     * isHosted : false
     * pillarId : pillar/sport
     * pillarName : Sport
     */

    @SerializedName("sectionName")
    private String sectionName;
    @SerializedName("webPublicationDate")
    private String webPublicationDate;
    @SerializedName("webTitle")
    private String webTitle;
    @SerializedName("webUrl")
    private String webUrl;
    @SerializedName("fields")
    private Fields fields;

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getWebPublicationDate() {
        return webPublicationDate;
    }

    public void setWebPublicationDate(String webPublicationDate) {
        this.webPublicationDate = webPublicationDate;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }
}

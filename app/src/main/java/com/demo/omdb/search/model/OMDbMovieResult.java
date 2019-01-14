package com.demo.omdb.search.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Maps to the JSON Response within the {@link OMDbResponse} object
 */
public class OMDbMovieResult {

    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Year")
    @Expose
    private String year;
    @SerializedName("Poster")
    @Expose
    private String posterURL;

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getPosterURL() {
        return posterURL;
    }
}

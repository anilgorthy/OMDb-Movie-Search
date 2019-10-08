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
    @SerializedName("Response")
    @Expose
    private boolean response;
    @SerializedName("Error")
    @Expose
    private String errorMessage;

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public boolean isResponse() {
        return response;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

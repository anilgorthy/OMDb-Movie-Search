package com.demo.omdb.search.model;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Maps to the JSON Response at the root level
 */
public class OMDbResponse {

    @Nullable
    @SerializedName("Search")
    @Expose
    private List<OMDbMovieResult> OMDbMovieResultList = null;

    @Nullable
    public List<OMDbMovieResult> getOMDbMovieResultList() {
        return OMDbMovieResultList;
    }

}

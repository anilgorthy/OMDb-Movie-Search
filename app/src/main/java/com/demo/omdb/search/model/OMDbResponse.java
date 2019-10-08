package com.demo.omdb.search.model;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Maps to the JSON Response at the root level
 *
 * TODO:
 * A sample successful response:
 *
 * {
 *   "Search": [
 *     {
 *       "Title": "Total Recall",
 *       "Year": "1990",
 *       "imdbID": "tt0100802",
 *       "Type": "movie",
 *       "Poster": "https://m.media-amazon.com/images/M/MV5BYzU1YmJjMGEtMjY4Yy00MTFlLWE3NTUtNzI3YjkwZTMxZjZmXkEyXkFqcGdeQXVyNDc2NjEyMw@@._V1_SX300.jpg"
 *     }, {...}, {...}
 *   ],
 *   "totalResults": "432",
 *   "Response": "True"
 * }
 *
 * A sample unsuccessful response:
 *
 * {"Response":"False","Error":"Too many results."}
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

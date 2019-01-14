package com.demo.omdb.search.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Handles the call to the OMDb API while also creating the query parameter
 */
public interface OMDbEndpoint {
    @GET("?apikey=c5661008")
    Call<OMDbResponse> searchTitles(@Query("s") String searchTerm);
}

package com.demo.omdb.search.model;

import android.support.annotation.NonNull;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Fetches the data from the OMDb API and handles response and error scenarios
 */
public class OMDbRepository {
    private static final String TAG = OMDbRepository.class.getSimpleName();
    private final OMDbEndpoint omdbEndpoint;
    private static final String BASE_URL = "http://www.omdbapi.com/";

    /**
     * Constructor that also initializes the networking library
     */
    public OMDbRepository() {
        Retrofit omdbRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        omdbEndpoint = omdbRetrofit.create(OMDbEndpoint.class);
    }

    /**
     * Called by the Presenter with the search query and
     * callback to get a response from the API endpoint
     * and pass it back to the {@link com.demo.omdb.search.presenter.OMDbPresenter}
     * @param query
     * @param callback
     */
    public void searchMovies(@NonNull final String query, @NonNull final OMDbRepositoryCallback callback) {
        Call<OMDbResponse> call = omdbEndpoint.searchTitles(query);
        call.enqueue(new Callback<OMDbResponse>() {
            @Override
            public void onResponse(Call<OMDbResponse> call, Response<OMDbResponse> response) {
                callback.handleOMDbResponse(response);
            }

            @Override
            public void onFailure(Call<OMDbResponse> call, Throwable t) {
                Log.e(TAG, "Error fetching response: " + t.getMessage());
                callback.handleOMDbError();
            }
        });
    }

    public interface OMDbRepositoryCallback {
        void handleOMDbResponse(Response<OMDbResponse> response);

        void handleOMDbError();
    }
}

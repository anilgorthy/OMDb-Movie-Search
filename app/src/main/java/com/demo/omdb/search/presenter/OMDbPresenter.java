package com.demo.omdb.search.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.omdb.search.model.OMDbRepository;
import com.demo.omdb.search.model.OMDbResponse;
import com.demo.omdb.search.view.OMDbViewContract;

import retrofit2.Response;

/**
 * Handles the communication with the View {@link com.demo.omdb.search.view.OMDbSearchActivity}
 * and the callback/response from the repository {@link OMDbRepository}
 */
public class OMDbPresenter implements OMDbPresenterContract,
                                OMDbRepository.OMDbRepositoryCallback {

    private static final String TAG = OMDbPresenter.class.getSimpleName();
    private final OMDbViewContract omdbViewContract;
    private final OMDbRepository omdbRepository;

    /**
     * Constructor
     * @param omdbViewContract
     * @param omdbRepository
     */
    public OMDbPresenter(OMDbViewContract omdbViewContract, OMDbRepository omdbRepository) {
        this.omdbViewContract = omdbViewContract;
        this.omdbRepository = omdbRepository;
    }

    /**
     * Receives the search query from the View and calls the repository
     * @param title
     */
    @Override
    public void searchMovieTitles(@NonNull String title) {
        Log.i(TAG, "Search term entered is: " + title);
        omdbRepository.searchMovies(title, this);
    }

    /**
     * Handles the response from the repository
     * @param response
     */
    @Override
    public void handleOMDbResponse(Response<OMDbResponse> response) {
        if (response.isSuccessful()) {
            OMDbResponse omdbResponse = response.body();
            if (omdbResponse != null && omdbResponse.getOMDbMovieResultList() != null) {
                Log.i(TAG, "Results count is: " + omdbResponse.getOMDbMovieResultList().size());
                omdbViewContract.displayMovieTitles(omdbResponse.getOMDbMovieResultList());
            } else {
                omdbViewContract.displayError();
            }
        } else {
            omdbViewContract.displayError();
        }
    }

    /**
     * For when the API response errors out
     */
    @Override
    public void handleOMDbError() {
        omdbViewContract.displayError();
    }
}

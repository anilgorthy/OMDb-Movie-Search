package com.demo.omdb.search.view;

import android.support.annotation.NonNull;

import com.demo.omdb.search.model.OMDbMovieResult;

import java.util.List;

/**
 * Specifies the contract between the View and Presenter
 */
public interface OMDbViewContract {
    void displayMovieTitles(@NonNull List<OMDbMovieResult> OMDbMovieResults);

    void displayError();
}

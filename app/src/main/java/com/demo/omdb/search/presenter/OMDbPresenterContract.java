package com.demo.omdb.search.presenter;

/**
 * Specifies the contract that the {@link OMDbPresenter} needs to implement
 */
public interface OMDbPresenterContract {
    void searchMovieTitles(String titleSearch);
}

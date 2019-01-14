package com.demo.omdb.search;

import android.util.Log;

import com.demo.omdb.search.model.OMDbMovieResult;
import com.demo.omdb.search.model.OMDbRepository;
import com.demo.omdb.search.model.OMDbResponse;
import com.demo.omdb.search.presenter.OMDbPresenter;
import com.demo.omdb.search.view.OMDbViewContract;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Collections;
import java.util.List;

import retrofit2.Response;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Log.class, Response.class})
public class OMDbPresenterTest {

    private OMDbPresenter omdbPresenter;
    @Mock
    private OMDbRepository omdbRepository;
    @Mock
    private OMDbViewContract omdbViewContract;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        //the framework/library classes need to be mocked
        PowerMockito.mockStatic(Log.class);
        PowerMockito.mockStatic(Response.class);

        omdbPresenter = Mockito.spy(new OMDbPresenter(omdbViewContract, omdbRepository));
    }

    /**
     * FYI: I didn't write any tests for null or empty search queries
     * since I annotated (@NonNull) the parameter to not take null
     */
    @Test
    public void searchMovieTitles_Invalid_Movie_Title() {
        String searchQuery = "some random name";

        omdbPresenter.searchMovieTitles(searchQuery);
        Mockito.verify(omdbRepository, Mockito.times(1)).searchMovies(searchQuery, omdbPresenter);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void handleOMDbResponse_Success() {
        Response response = Mockito.mock(Response.class);
        OMDbResponse searchResponse = Mockito.mock(OMDbResponse.class);
        Mockito.doReturn(true).when(response).isSuccessful();
        Mockito.doReturn(searchResponse).when(response).body();
        List<OMDbMovieResult> searchResults = Collections.singletonList(new OMDbMovieResult());

        Mockito.doReturn(searchResults).when(searchResponse).getOMDbMovieResultList();
        omdbPresenter.handleOMDbResponse(response);

        Mockito.verify(omdbViewContract, Mockito.times(1)).displayMovieTitles(searchResults);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void handleOMDbResponse_Failure() {
        Response response = Mockito.mock(Response.class);
        Mockito.doReturn(false).when(response).isSuccessful();

        omdbPresenter.handleOMDbResponse(response);

        Mockito.verify(omdbViewContract, Mockito.times(1)).displayError();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void handleOMDbResponse_EmptyResponse() {
        Response response = Mockito.mock(Response.class);
        Mockito.doReturn(true).when(response).isSuccessful();
        Mockito.doReturn(null).when(response).body();

        omdbPresenter.handleOMDbResponse(response);

        Mockito.verify(omdbViewContract, Mockito.times(1)).displayError();
    }

}
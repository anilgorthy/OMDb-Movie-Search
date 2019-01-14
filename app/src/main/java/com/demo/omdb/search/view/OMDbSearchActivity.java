package com.demo.omdb.search.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.omdb.search.R;
import com.demo.omdb.search.model.OMDbMovieResult;
import com.demo.omdb.search.model.OMDbRepository;
import com.demo.omdb.search.presenter.OMDbPresenter;
import com.demo.omdb.search.presenter.OMDbPresenterContract;
import com.demo.omdb.search.presenter.OMDbSearchAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Displays search functionality and results
 * {@link OMDbMovieResult} in a card view
 */
public class OMDbSearchActivity extends AppCompatActivity implements OMDbViewContract {

    private static final String TAG = OMDbSearchActivity.class.getSimpleName();
    private OMDbSearchAdapter omdbSearchAdapter;
    private LinearLayoutManager layoutManager;

    @BindView(R.id.searchTerm)
    EditText searchTerm;

    @BindView(R.id.omdbRV)
    RecyclerView movieResultsRV;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        OMDbRepository omdbRepository = new OMDbRepository();
        final OMDbPresenterContract omdbPresenterContract = new OMDbPresenter(this, omdbRepository);
        searchTerm.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v,
                                          int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Log.i(TAG, "Search term entered is: " + searchTerm.getText().toString());
                    omdbPresenterContract.searchMovieTitles(searchTerm.getText().toString());
                    progressBar.setVisibility(View.VISIBLE);
                    return true;
                }
                return false;
            }
        });

        layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        movieResultsRV.setLayoutManager(layoutManager);
        omdbSearchAdapter = new OMDbSearchAdapter();
        movieResultsRV.setAdapter(omdbSearchAdapter);
    }

    @Override
    public void displayMovieTitles(@NonNull List<OMDbMovieResult> OMDbMovieResults) {
        progressBar.setVisibility(View.GONE);
        hideKeyboard(this);
        omdbSearchAdapter.updateResults(OMDbMovieResults);
    }

    @Override
    public void displayError() {
        Toast.makeText(this, this.getResources().getString(R.string.search_error_message), Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
    }

    private void hideKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(), 0);
        }
    }
}



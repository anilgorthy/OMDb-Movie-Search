package com.demo.omdb.search.presenter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.omdb.search.R;
import com.demo.omdb.search.model.OMDbMovieResult;
import com.squareup.picasso.Callback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter that is used by the RecyclerView to display {@link OMDbMovieResult} item in the view
 */
public class OMDbSearchAdapter extends RecyclerView.Adapter<OMDbSearchAdapter.OMDbViewHolder> {

    private static final String TAG = OMDbSearchAdapter.class.getSimpleName();
    private List<OMDbMovieResult> results = new ArrayList<>();

    @NonNull
    @Override
    public OMDbViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                             int viewType) {
        final View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_details_item, parent, false);
        return new OMDbViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull OMDbViewHolder holder, int position) {
        holder.bind(results.get(position));
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public void updateResults(@NonNull List<OMDbMovieResult> results) {
        this.results = results;
        notifyDataSetChanged();
    }

    static class OMDbViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.titleValTV)
        TextView movieTitleTV;

        @BindView(R.id.yearValTV)
        TextView movieYearTV;

        @BindView(R.id.posterIV)
        PicassoImageView moviePosterIV;

        OMDbViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(OMDbMovieResult searchResult) {
            movieTitleTV.setText(searchResult.getTitle());
            movieYearTV.setText(searchResult.getYear());
            moviePosterIV.setImageUrl(searchResult.getPosterURL(), new Callback() {
                @Override
                public void onSuccess() {
                    Log.i(TAG, "Image downloaded successfully");
                }

                @Override
                public void onError() {
                    Log.e(TAG, "Error downloading Image");
                }
            });
        }
    }
}

package com.demo.omdb.search.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.demo.omdb.search.R;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import okhttp3.OkHttpClient;

/**
 * Wrapper for the image downloading library that downloads
 * the Poster URL. Called by {@link OMDbSearchAdapter}
 */
public class PicassoImageView extends AppCompatImageView {

    private static final String OMDB_POSTER_MISSING_URL = "N/A";

    @SuppressLint("StaticFieldLeak")
    private static Picasso picasso;

    public PicassoImageView(final Context context) {
        super(context);
        init();
    }

    public PicassoImageView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PicassoImageView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setImageUrl(final String url, final Callback callback) {
        if (OMDB_POSTER_MISSING_URL.equals(url)) {
            //sometimes Poster URL is unavailable and returned as "N/A" so, I'm displaying a placeholder
            picasso.load(R.drawable.omdb_icon)
                    .placeholder(this.getResources().getDrawable(R.drawable.omdb_icon))
                    .resize(300,300).centerCrop()
                    .into(this, callback);

        } else {
            picasso.load(Uri.parse(url)).into(this, callback);
        }
    }

    private void init() {
        if(picasso == null) {
            picasso = new Picasso.Builder(getContext().getApplicationContext())
                    .downloader(new OkHttp3Downloader(new OkHttpClient()))
                    .build();
        }
    }

}


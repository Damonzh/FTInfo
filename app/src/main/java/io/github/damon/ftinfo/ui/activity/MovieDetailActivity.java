package io.github.damon.ftinfo.ui.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.damon.ftinfo.R;
import io.github.damonzh.ftinfo.API;
import io.github.damonzh.ftinfo.bean.Movies;

/**
 * Author:      ZhangYan
 * Date:        15/12/25
 * Description:
 */
public class MovieDetailActivity extends AppCompatActivity {

    private static final String TAG = MovieDetailActivity.class.getSimpleName();

    @Bind(R.id.iv_movie_poster)
    ImageView mMoviePoster;
    @Bind(R.id.tv_movie_over_view)
    TextView mMovieOverView;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        Movies movies = (Movies) getIntent().getSerializableExtra(MoviesActivity.EXTRA_MOVIE);
        mMoviePoster.setTransitionName(movies.getTitle());
        mMovieOverView.setText(movies.getOverview());
        Picasso.with(this).load(API.IMAGE_BASE_URL + movies.getBackdrop_path()).into(mMoviePoster);
    }
}

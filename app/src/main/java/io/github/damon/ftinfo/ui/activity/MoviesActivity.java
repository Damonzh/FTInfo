package io.github.damon.ftinfo.ui.activity;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.damon.ftinfo.R;
import io.github.damon.ftinfo.presenter.impl.MoviePresenterImpl;
import io.github.damon.ftinfo.ui.adapter.MovieAdapter;
import io.github.damon.ftinfo.view.IMovieView;
import io.github.damon.ftinfo.view.RecyclerInsetsDecoration;
import io.github.damonzh.ftinfo.bean.Movies;

/**
 * Author:      ZhangYan
 * Date:        15/12/25
 * Description:
 */
public class MoviesActivity extends AppCompatActivity implements IMovieView, MovieAdapter.OnItemClickListener {

    private static final String TAG = MoviesActivity.class.getSimpleName();

    public static final String EXTRA_MOVIE = "movie";

    @Bind(R.id.rv_movie_list)
    RecyclerView mMovieList;

    @Bind(R.id.tool_bar)
    Toolbar mToolBar;

    private MovieAdapter mMovieAdapter;
    private LinearLayoutManager mLayoutManager;
    private MoviePresenterImpl mMoviePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(mToolBar);
        mMovieList.setLayoutManager(mLayoutManager = new LinearLayoutManager(this));
        mMovieList.addItemDecoration(new RecyclerInsetsDecoration(this));
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mMoviePresenter = new MoviePresenterImpl(this);
        mMovieList.addOnScrollListener(mScrollListener);
        mMoviePresenter.start();
    }

    @Override
    public void showMovies(List<Movies> moviesList) {
        mMovieAdapter = new MovieAdapter(this, moviesList);
        mMovieList.setAdapter(mMovieAdapter);
        mMovieAdapter.setOnItemClickListener(this);
    }

    @Override
    public void showMoreMovies(List<Movies> moreMoviesList) {
        mMovieAdapter.addMoreMovies(moreMoviesList);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMoviePresenter.stop();
    }

    @Override
    public void finishRefresh() {
      //TODO 下拉刷新时使用 暂时去掉下拉刷新
    }

    @Override
    public void finishLoadMore() {
        //移除ProgressBar
        mMovieAdapter.removeMovie(mMovieAdapter.getAllMovies().size() - 1);
    }

    @Override
    public void showErrorToast(String errMsg) {
        Toast.makeText(MoviesActivity.this, errMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean isListEmpty() {
        return mMovieAdapter == null || mMovieAdapter.getAllMovies().isEmpty();
    }

    private RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {
        private boolean isToolBarVisiable = true;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int visibleItemCount = recyclerView.getLayoutManager().getChildCount();
            int totalItemCount = recyclerView.getLayoutManager().getItemCount();
            int pastVisibleItems = ((LinearLayoutManager) recyclerView.getLayoutManager())
                    .findFirstVisibleItemPosition();

            if ((visibleItemCount + pastVisibleItems) >= totalItemCount && !mMoviePresenter.isLoading()) {
                //添加一个null元素，判断是该Item就是ProgressBar
                mMovieAdapter.addMovie(null);
                mMoviePresenter.onEndListReached();
            }

            //先来判断隐藏和显示toolbar
            /*if (dy > 10) { //上滚 toolBar需要消失
                if (isToolBarVisiable) {//如果可见  则让其消失
                    hideToolBar();
                    isToolBarVisiable = false;
                }
            } else if (dy < -10) {//下滚 toolBar需要显示
                if (!isToolBarVisiable) {//如果不可见 则让其显示
                    showToolBar();
                    isToolBarVisiable = true;
                }
            }*/
        }
    };

    private void showToolBar() {
        mToolBar.startAnimation(AnimationUtils.loadAnimation(this, R.anim.translation_down));
    }

    private void hideToolBar() {
        mToolBar.startAnimation(AnimationUtils.loadAnimation(this, R.anim.translation_up));
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onItemClick(View view, int position) {
        Movies movies = mMovieAdapter.getItemAtPosition(position);
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movies);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, view, movies.getTitle());
        startActivity(intent, options.toBundle());
    }
}

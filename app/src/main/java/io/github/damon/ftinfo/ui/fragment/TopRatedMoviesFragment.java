package io.github.damon.ftinfo.ui.fragment;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.damon.ftinfo.R;
import io.github.damon.ftinfo.presenter.impl.MoviePresenterImpl;
import io.github.damon.ftinfo.ui.activity.MovieDetailActivity;
import io.github.damon.ftinfo.ui.activity.MoviesActivity;
import io.github.damon.ftinfo.ui.adapter.MovieAdapter;
import io.github.damon.ftinfo.view.IMovieView;
import io.github.damon.ftinfo.view.RecyclerInsetsDecoration;
import io.github.damonzh.ftinfo.bean.Movies;
import io.github.damonzh.ftinfo.bean.MoviesWrapper;

/**
 * Author:      ZhangYan
 * Date:        15/12/26
 * Description:
 */
public class TopRatedMoviesFragment extends Fragment implements IMovieView, MovieAdapter.OnItemClickListener{
    public static final String EXTRA_MOVIE = "movie";

    @Bind(R.id.rv_movie_list)
    RecyclerView mMovieList;

    protected MovieAdapter mMovieAdapter;
    protected LinearLayoutManager mLayoutManager;
    protected MoviePresenterImpl mMoviePresenter;

    protected MoviesWrapper.MOVIE_TYPE movieType;

    private View mContentView;

    public TopRatedMoviesFragment() {
        movieType = MoviesWrapper.MOVIE_TYPE.TOP_RATED;
        mMoviePresenter = new MoviePresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mContentView == null) {
            mContentView = inflater.inflate(R.layout.fragment_movies, container, false);
            ButterKnife.bind(this, mContentView);
            mMovieList.setLayoutManager(mLayoutManager = new LinearLayoutManager(getActivity()));
            mMovieList.addItemDecoration(new RecyclerInsetsDecoration(getActivity()));
            mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mMovieList.addOnScrollListener(mScrollListener);
        }
        return mContentView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!mMoviePresenter.hasStarted()) {
            mMoviePresenter.start(movieType);
        }
    }

    @Override
    public void showMovies(List<Movies> moviesList) {
        mMovieAdapter = new MovieAdapter(getActivity(), moviesList);
        mMovieList.setAdapter(mMovieAdapter);
        mMovieAdapter.setOnItemClickListener(this);
    }

    @Override
    public void showMoreMovies(List<Movies> moreMoviesList) {
        mMovieAdapter.addMoreMovies(moreMoviesList);
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
        Toast.makeText(getActivity(), errMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean isListEmpty() {
        return mMovieAdapter == null || mMovieAdapter.getAllMovies().isEmpty();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {
//        private boolean isToolBarVisiable = true;

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
                mMoviePresenter.onEndListReached(movieType);
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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onItemClick(View view, int position) {
        Movies movies = mMovieAdapter.getItemAtPosition(position);
        Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movies);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(), view, movies.getTitle());
        ((MoviesActivity) getActivity()).startActivity(intent, options.toBundle());
    }
}

package io.github.damonzh.ftinfo.model.impl;

import de.greenrobot.event.EventBus;
import io.github.damonzh.ftinfo.API;
import io.github.damonzh.ftinfo.bean.MoviesWrapper;
import io.github.damonzh.ftinfo.model.IMoviesModel;
import io.github.damonzh.ftinfo.rest.APIConstant;
import io.github.damonzh.ftinfo.rest.MoviesService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Author:      ZhangYan
 * Date:        15/12/24
 * Description:
 */
public class MoviesModelImpl implements IMoviesModel {
    private int mPopularPage = 1;
    private int mTopRatedPage = 1;
    private int mLatestPage = 1;
    private int mUpcomingPage = 1;
    private int mNowPlayingPage = 1;



    private RestAdapter mRestAdapter = new RestAdapter.Builder().setEndpoint(API.HOST_URL).build();
    private MoviesService mMoviesService = mRestAdapter.create(MoviesService.class);

    @Override
    public void getPopularMovies() {
        mMoviesService.getPopularMovies(API.API_KEY, mPopularPage, APIConstant.LANGUAGE, new Callback<MoviesWrapper>() {
            @Override
            public void success(MoviesWrapper moviesWrapper, Response response) {
                moviesWrapper.setMovieType(MoviesWrapper.MOVIE_TYPE.POPULAR);
                EventBus.getDefault().post(moviesWrapper);
                mPopularPage++;
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(error);
            }
        });
    }

    @Override
    public void getTopRatedMovies() {
        mMoviesService.getTopRatedMovies(API.API_KEY, mTopRatedPage, new Callback<MoviesWrapper>() {
            @Override
            public void success(MoviesWrapper moviesWrapper, Response response) {
                moviesWrapper.setMovieType(MoviesWrapper.MOVIE_TYPE.TOP_RATED);
                EventBus.getDefault().post(moviesWrapper);
                mTopRatedPage++;
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(error);
            }
        });
    }

    @Override
    public void getNowPlayingMovies() {
        mMoviesService.getNowPlayingMovies(API.API_KEY, mNowPlayingPage, APIConstant.LANGUAGE, new Callback<MoviesWrapper>() {
            @Override
            public void success(MoviesWrapper moviesWrapper, Response response) {
                moviesWrapper.setMovieType(MoviesWrapper.MOVIE_TYPE.NOW_PLAYING);
                EventBus.getDefault().post(moviesWrapper);
                mNowPlayingPage++;
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(error);
            }
        });
    }

    @Override
    public void getUpcomingMovies() {
        mMoviesService.getUpcomintMovies(API.API_KEY, mUpcomingPage, APIConstant.LANGUAGE, new Callback<MoviesWrapper>() {
            @Override
            public void success(MoviesWrapper moviesWrapper, Response response) {
                moviesWrapper.setMovieType(MoviesWrapper.MOVIE_TYPE.UP_COMING);
                EventBus.getDefault().post(moviesWrapper);
                mUpcomingPage++;
            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(error);
            }
        });
    }

    @Override
    public void getLatestMovies() {
        mMoviesService.getLatestMovies(API.API_KEY, new Callback<MoviesWrapper>() {
            @Override
            public void success(MoviesWrapper moviesWrapper, Response response) {
                moviesWrapper.setMovieType(MoviesWrapper.MOVIE_TYPE.LATEST);
                EventBus.getDefault().post(moviesWrapper);

            }

            @Override
            public void failure(RetrofitError error) {
                EventBus.getDefault().post(error);
            }
        });
    }


}

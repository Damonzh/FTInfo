package io.github.damonzh.ftinfo.model.impl;

import de.greenrobot.event.EventBus;
import io.github.damonzh.ftinfo.API;
import io.github.damonzh.ftinfo.bean.Movies;
import io.github.damonzh.ftinfo.bean.MoviesWrapper;
import io.github.damonzh.ftinfo.model.IMoviesModel;
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

    @Override
    public void getPopularMovies() {
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API.HOST_URL).build();
        final MoviesService moviesService = restAdapter.create(MoviesService.class);
        moviesService.getPopularMovies(API.API_KEY, mPopularPage, "en", new Callback<MoviesWrapper>() {
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
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API.HOST_URL).build();
        MoviesService moviesService = restAdapter.create(MoviesService.class);
        moviesService.getTopRatedMovies(API.API_KEY, mTopRatedPage, new Callback<MoviesWrapper>() {
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

}

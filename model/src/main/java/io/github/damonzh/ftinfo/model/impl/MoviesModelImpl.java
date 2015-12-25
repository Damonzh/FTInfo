package io.github.damonzh.ftinfo.model.impl;

import de.greenrobot.event.EventBus;
import io.github.damonzh.ftinfo.API;
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
    private int mCurrentPage = 1;

    @Override
    public void getPopularMovies() {
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(API.HOST_URL).build();
        MoviesService moviesService = restAdapter.create(MoviesService.class);
        moviesService.getPopularMovies(API.API_KEY,mCurrentPage,"en",callback);
    }

    public Callback callback = new Callback() {
        @Override
        public void success(Object o, Response response) {
            if (o instanceof MoviesWrapper){
                MoviesWrapper moviesWrapper = (MoviesWrapper) o;
                EventBus.getDefault().post(moviesWrapper);
                mCurrentPage++;
            }
        }

        @Override
        public void failure(RetrofitError error) {
            EventBus.getDefault().post(error);
        }
    };
}

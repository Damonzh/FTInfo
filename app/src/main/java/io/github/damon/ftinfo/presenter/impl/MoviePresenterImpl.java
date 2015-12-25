package io.github.damon.ftinfo.presenter.impl;

import de.greenrobot.event.EventBus;
import io.github.damon.ftinfo.presenter.IPresenter;
import io.github.damon.ftinfo.view.IMovieView;
import io.github.damonzh.ftinfo.API;
import io.github.damonzh.ftinfo.bean.MoviesWrapper;
import io.github.damonzh.ftinfo.model.IConfigurationModel;
import io.github.damonzh.ftinfo.model.IMoviesModel;
import io.github.damonzh.ftinfo.model.impl.ConfigurationModelImpl;
import io.github.damonzh.ftinfo.model.impl.MoviesModelImpl;
import retrofit.RetrofitError;

/**
 * Author:      ZhangYan
 * Date:        15/12/25
 * Description:
 */
public class MoviePresenterImpl implements IPresenter {
    private IMovieView mMovieView;
    private IMoviesModel mMovieModel;
    private IConfigurationModel mConfigurationModel;
    private EventBus mEventBus;
    private boolean isLoading = true;

    public MoviePresenterImpl(IMovieView mMovieView) {
        this.mMovieView = mMovieView;
        this.mMovieModel = new MoviesModelImpl();
        this.mConfigurationModel = new ConfigurationModelImpl();
    }

    public void start() {
        mEventBus = EventBus.getDefault();
        if (!mEventBus.isRegistered(this)) {
            mEventBus.register(this);
        }
        mConfigurationModel.getConfiguration();
    }

    @Override
    public void stop() {
        if (mEventBus != null) {
            mEventBus.unregister(this);
        }
    }

    /**
     * 成功获取到数据
     *
     * @param moviesWrapper
     */
    public void onEventMainThread(MoviesWrapper moviesWrapper) {

        if (mMovieView.isListEmpty()){
            //首次加载数据
            mMovieView.showMovies(moviesWrapper.getResults());
        }else{
            //上拉加载
            mMovieView.finishLoadMore();
            mMovieView.showMoreMovies(moviesWrapper.getResults());
        }
        isLoading = false;
    }

    public void onEventMainThread(String imageBaseUrl) {
        API.IMAGE_BASE_URL = imageBaseUrl;
        getPopularMovies();
    }

    /**
     * 加载数据失败
     * @param error
     */
    public void onEventMainThread(RetrofitError error){
        if (mMovieView.isListEmpty()) {
            mMovieView.finishRefresh();
        }else {
            mMovieView.finishLoadMore();
        }
        isLoading = false;
        mMovieView.showErrorToast("网络出错啦");
    }

    public void getPopularMovies() {
        mMovieModel.getPopularMovies();
    }

    public void onEndListReached() {
        getPopularMovies();
        isLoading = true;
    }

    public boolean isLoading(){
        return isLoading;
    }
}

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

    private EventBus mEventBus;
    private boolean isLoading = true;
    private MoviesWrapper.MOVIE_TYPE mMovieType;

    private boolean hasStarted = false;

    public MoviePresenterImpl(IMovieView mMovieView) {
        this.mMovieView = mMovieView;
        this.mMovieModel = new MoviesModelImpl();

    }

    public void start(MoviesWrapper.MOVIE_TYPE movieType) {
        mEventBus = EventBus.getDefault();
        if (!mEventBus.isRegistered(this)) {
            mEventBus.register(this);
        }
        mMovieType = movieType;
        hasStarted = true;
        getMovies(movieType);
    }

    @Override
    public void stop() {
        if (mEventBus != null) {
            mEventBus.unregister(this);
        }
    }

    public boolean hasStarted() {
        return hasStarted;
    }

    /**
     * 成功获取到数据
     *
     * @param moviesWrapper
     */
    public void onEventMainThread(MoviesWrapper moviesWrapper) {
        if (mMovieType == moviesWrapper.getMovieType()) {
            if (mMovieView.isListEmpty()) {
                mMovieView.showMovies(moviesWrapper.getResults());
                //首次加载数据
            } else {
                //上拉加载
                mMovieView.finishLoadMore();
                mMovieView.showMoreMovies(moviesWrapper.getResults());
            }
            isLoading = false;
        }
    }

    /**
     * 加载数据失败
     *
     * @param error
     */
    public void onEventMainThread(RetrofitError error) {
        if (mMovieView.isListEmpty()) {
            mMovieView.finishRefresh();
        } else {
            mMovieView.finishLoadMore();
        }
        isLoading = false;
        mMovieView.showErrorToast("网络出错啦");
    }

    public void getMovies(MoviesWrapper.MOVIE_TYPE movieType) {
        switch (movieType) {
            case POPULAR:
                mMovieModel.getPopularMovies();
                break;
            case TOP_RATED:
                mMovieModel.getTopRatedMovies();
                break;
        }
    }

    public void onEndListReached(MoviesWrapper.MOVIE_TYPE movieType) {
        getMovies(movieType);
        isLoading = true;
    }

    public boolean isLoading() {
        return isLoading;
    }
}

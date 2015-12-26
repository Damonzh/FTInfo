package io.github.damon.ftinfo.presenter;

import io.github.damonzh.ftinfo.bean.MoviesWrapper;

/**
 * Author:      ZhangYan
 * Date:        15/12/25
 * Description:
 */
public interface IPresenter {
    void start(MoviesWrapper.MOVIE_TYPE movieType);

    void stop();
}

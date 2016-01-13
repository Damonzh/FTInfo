package io.github.damonzh.ftinfo.model;

/**
 * Author:      ZhangYan
 * Date:        15/12/24
 * Description:
 */
public interface IMoviesModel {

    /**
     * 获取流行影片 每天更新
     */
    void getPopularMovies();

    /**
     * 获取高评分的影片 每天更新
     */
    void getTopRatedMovies();

    /**
     * 获取一周新片 每天更新
     */
    void getNowPlayingMovies();

    /**
     * 获取即将上映影片 每天更新
     */
    void getUpcomingMovies();


    /**
     * 获取最新影片
     */
    void getLatestMovies();

}

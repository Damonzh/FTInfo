package io.github.damon.ftinfo.view;

import java.util.List;

import io.github.damonzh.ftinfo.bean.Movies;

/**
 * Author:      ZhangYan
 * Date:        15/12/25
 * Description:
 */
public interface IMovieView {

    /**
     * 显示movie列表
     * @param moviesList movie列表
     */
    void showMovies(List<Movies> moviesList);

    /**
     * 显示更多movie列表
     * @param moreMoviesList 更多movie列表
     */
    void showMoreMovies(List<Movies> moreMoviesList);

    /**
     * 刷新结束
     */
    void finishRefresh();

    /**
     * 加载更多结束
     */
    void finishLoadMore();

    /**
     * 弹出错误信息
     * @param errMsg 错误信息
     */
    void showErrorToast(String errMsg);

    /**
     * 列表是否为空，是否有数据
     * @return
     */
    boolean isListEmpty();
}

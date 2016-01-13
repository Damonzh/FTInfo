package io.github.damonzh.ftinfo.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Author:      ZhangYan
 * Date:        15/12/24
 * Description:
 */
public class MoviesWrapper implements Serializable {

    public enum MOVIE_TYPE {
        POPULAR(0),//最近流行
        TOP_RATED(1),//高分电影
        UP_COMING(2),//即将上映
        NOW_PLAYING(3),//正在上映
        LATEST(4);//最新影片

        private int mType = 0;

        MOVIE_TYPE(int type) {
            mType = type;
        }

        public int getType(){
           return mType;
        }
    }

    private MOVIE_TYPE movieType;

    private String page;

    private String total_pages;

    private String total_results;

    private List<Movies> results;

    public MOVIE_TYPE getMovieType() {
        return movieType;
    }

    public void setMovieType(MOVIE_TYPE movieType) {
        this.movieType = movieType;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(String total_pages) {
        this.total_pages = total_pages;
    }

    public String getTotal_results() {
        return total_results;
    }

    public void setTotal_results(String total_results) {
        this.total_results = total_results;
    }

    public List<Movies> getResults() {
        return results;
    }

    public void setResults(List<Movies> results) {
        this.results = results;
    }
}

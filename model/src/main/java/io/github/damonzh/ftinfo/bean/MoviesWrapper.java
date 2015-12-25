package io.github.damonzh.ftinfo.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Author:      ZhangYan
 * Date:        15/12/24
 * Description:
 */
public class MoviesWrapper implements Serializable{
    private String page;

    private String total_pages;

    private String total_results;

    private List<Movies> results;

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

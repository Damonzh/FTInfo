package io.github.damonzh.ftinfo.rest;


import io.github.damonzh.ftinfo.bean.ConfigurationResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Author:      ZhangYan
 * Date:        15/12/24
 * Description:
 */
public interface ConfigurationService {

    @GET("/configuration")
    void getConfiguration(@Query("api_key") String apiKey, Callback<ConfigurationResponse> callBack);
}

package io.github.damonzh.ftinfo.model.impl;

import de.greenrobot.event.EventBus;
import io.github.damonzh.ftinfo.API;
import io.github.damonzh.ftinfo.bean.ConfigurationResponse;
import io.github.damonzh.ftinfo.model.IConfigurationModel;
import io.github.damonzh.ftinfo.rest.ConfigurationService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Author:      ZhangYan
 * Date:        15/12/24
 * Description:
 */
public class ConfigurationModelImpl implements IConfigurationModel {

    private final String QUALITY_DESIRED    = "w780";
    private final String QUALITY_ORIGINAL   = "original";

    @Override
    public void getConfiguration() {

        RestAdapter retrofit = new RestAdapter.Builder().setEndpoint(API.HOST_URL).build();
        ConfigurationService configurationService = retrofit.create(ConfigurationService.class);
        configurationService.getConfiguration(API.API_KEY, retrofitCallBack);
    }

    public Callback retrofitCallBack = new Callback() {
        @Override
        public void success(Object o, Response response) {
            if (o instanceof ConfigurationResponse) {
                ConfigurationResponse configurationResponse = (ConfigurationResponse) o;
                configurationImgUrl(configurationResponse);
            }
        }

        @Override
        public void failure(RetrofitError error) {
            EventBus.getDefault().post(error);
        }
    };

    private void configurationImgUrl(ConfigurationResponse configurationResponse) {
        String url = "";
        if (configurationResponse.getImages() != null){
            String imageQuality = "";
            url = configurationResponse.getImages().getBase_url();
            for (String quality : configurationResponse.getImages().getBackdrop_sizes()){
                if (QUALITY_DESIRED.equals(quality)){
                    imageQuality = QUALITY_DESIRED;
                    break;
                }
            }

            if (imageQuality.equals("")){
                imageQuality = QUALITY_ORIGINAL;
            }
            url += imageQuality;
        }
        EventBus.getDefault().post(url);
    }
}

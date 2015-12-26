package io.github.damon.ftinfo.presenter.impl;

import de.greenrobot.event.EventBus;
import io.github.damon.ftinfo.presenter.IPresenter;
import io.github.damon.ftinfo.view.IHomeView;
import io.github.damonzh.ftinfo.API;
import io.github.damonzh.ftinfo.bean.MoviesWrapper;
import io.github.damonzh.ftinfo.model.IConfigurationModel;
import io.github.damonzh.ftinfo.model.impl.ConfigurationModelImpl;

/**
 * Author:      ZhangYan
 * Date:        15/12/26
 * Description:
 */
public class ConfigurationPresenter implements IPresenter {

    private IConfigurationModel mConfigurationModel;
    private IHomeView mHomeView;

    private EventBus mEventBus;

    public ConfigurationPresenter(IHomeView homeView) {
        mHomeView = homeView;
        this.mConfigurationModel = new ConfigurationModelImpl();
    }

    @Override
    public void start(MoviesWrapper.MOVIE_TYPE movieType) {
        mEventBus = EventBus.getDefault();
        if (!mEventBus.isRegistered(this)) {
            mEventBus.register(this);
            mConfigurationModel.getConfiguration();
        }
    }

    /**
     * 获取配置信息成功，可以访问接口了
     *
     * @param imageBaseUrl
     */
    public void onEventMainThread(String imageBaseUrl) {
        API.IMAGE_BASE_URL = imageBaseUrl;
        mHomeView.attatchFragment();
    }


    @Override
    public void stop() {
        if (mEventBus != null && mEventBus.isRegistered(this)) {
            mEventBus.unregister(this);
        }
    }
}

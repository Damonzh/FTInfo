package io.github.damonzh.ftinfo.model;

/**
 * Author:      ZhangYan
 * Date:        15/12/24
 * Description:
 */
public interface IConfigurationModel {

    /**
     * 获取配置信息，包括图片的base_url。官方建议将这些信息缓存到本地，并定期进行更新
     */
    void getConfiguration();

}

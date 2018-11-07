package com.ads.utils;

import com.ksc.ad.sdk.KsyunAdSdkConfig;

/**
 * Company: ksyun;<p/>
 * Author: HeHaoNan;<p/>
 * Date: 2017/12/28,上午10:12;<p/>
 * Package_Name: com.ksc.ad.demo;<p/>
 * Description: ;<p/>
 * Other: ;
 */
public class DemoConstants {
    public static final String INIT = "init";
    public static final String LoadSingleAd = "LoadSingleAd";

    /**
     * sdk app id
     */
    public static final String KEY_APP_ID = "app_id";
    /**
     * sdk环境
     */
    public static final String KEY_SDK_ENV = "sdk_env";
    /**
     * sdk展示广告是否显示关闭按钮
     */
    public static final String KEY_SDK_SHOW_CLOSE = "sdk_show_close";
    /**
     * sdk展示广告关闭按钮显示时间(秒)
     */
    public static final String KEY_SDK_SHOW_CLOSE_TIME = "sdk_show_time";
    /**
     * 是否只允许播放本地视频
     */
    public static final String KEY_SDK_SHOW_LOCAL = "sdk_show_local";
    /**
     * 是否关闭移动网络下缓存视频
     */
    public static final String KEY_SDK_CLOSE_MOBILE_LOAD = "sdk_close_mobile_load";

    /**
     * 开屏广告位Id
     */
    public static final String KEY_SDK_SPLASH_SLOT_ID = "key_sdk_splash_slot_id";

    //sdk 开屏广告位id 默认值
    public static final String VALUE_ADSLOT_ID ="d3b0f7cf";

    //activity跳转key
    public static final String KEY_AD_INFO = "key_ad_info";

    //sdk默认值
    public static final String VALUE_APP_ID = getAppId();
    public static final boolean VALUE_SDK_SHOW_CLOSE = true;
    public static final int VALUE_SDK_SHOW_TIME = 5;
    public static final int VALUE_SDK_ENV = KsyunAdSdkConfig.SANDBOX_ENV;
    public static final boolean VALUE_SDK_SHOW_LOCAL = false;
    public static final boolean VALUE_SDK_CLOSE_MOBILE_LOAD = false;

    public static String[] envArray = {
            "1:Sandbox Env",
            "2:Release Env"
    };

    public static final String VIDEO = "9"; //奖励视频
    public static final String SPLASH = "4";//开屏
    public static final String INTERSTITIAL = "2";//插屏

    public static final String ENUM_AD_SLOT_DEFAULT = "0"; //默认值，没有实际意义
    public static final String ENUM_AD_SLOT_DELIVERING = "1"; //上线
    public static final String ENUM_AD_SLOT_NOT_DELIVER = "2"; //下线

    public static final int ENIM_HAVE_NO_EFFECTIVE_AD = -1;//不存在有效广告

    private static String getAppId() {
        return "8b6e18b3";
    }

}

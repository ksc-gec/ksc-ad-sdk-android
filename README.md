# 金山云移动广告SDK接口文档 V1.2.0

**2017-12-19 V1.0.0 版本 - 汤洛**
1.初版更新
**2017-12-27 V1.1.0 版本 - 汤洛**
1.修改部分接口
2.添加SDK配置项接口说明
**2017-1-5 V1.2.0 版本 - 汤洛**
1.添加生命周期及清除缓存接口

### 目录

[TOC]

###1、SDK对外接口
####1.1 初始化接口
**接口名称**：
init

**方法签名**：
void init(Context context, String appId, String channelId, KsyunAdSdkConfig config, IKsyunAdInitResultListener initResultListener);

**参数说明**：
| 名称 | 类型 | 描述|
| ---- | ---- | ---- |
| context| Context |初始化时的上下文环境|
| appId| String |媒体（应用）唯一Id，由SSP平台创建媒体时分配|
| channelId| String |选填，媒体渠道唯一Id，由SSP平台创建媒体渠道时，根据平台渠道定义填写|
| config| KsyunAdSdkConfig |选填，SDK配置文件，键值对形式保存配置信息。具体键值定义，请参见附录1|
| initResultListener| IKsyunAdInitResultListener |初始化结果回调接口|

**回调说明**：

```
public interface IKsyunAdInitResultListener {
    //初始化成功时回调
    void onSuccess(Map<String, String> map);
    //初始化失败时回调
    void onFailure(int errCode, String errMsg);
}
```

**回调参数说明**：
| 名称 | 类型 | 描述|
| ---- | ---- | ---- |
| map| Map | 初始化成功时，如包含返回值，以Map形式返回。具体返回值Key定义，请参见附录2。目前版本暂无返回内容，此处留作扩展|
| errCode| int | SDK错误码|
| errMsg| String | SDK错误详情|

####1.2 预加载广告资源接口
**接口名称**：
preloadAd

**方法签名**：
void preloadAd(IKsyunAdPreloadListener preloadListener);

**参数说明**：
| 名称 | 类型 | 描述|
| ---- | ---- | ---- |
| preloadListener| IKsyunAdPreloadListener |预加载回调接口|

**回调说明**：

```
public interface IKsyunAdPreloadListener {
    //获取广告信息成功时回调
    void onAdInfoSuccess();
    //获取广告信息失败时回调
    void onAdInfoFailed(int errCode, String errMsg);
    //广告资源预加载完毕，会被回调多次
    void onAdLoaded(String adSlotId);
}

```

**回调参数说明**：
| 名称 | 类型 | 描述|
| ---- | ---- | ---- |
| adSlotId| String | 当前事件对应的广告位Id|
| errCode| int | 失败时错误码|
| errMsg| String | 失败时错误详情|

####1.3 判断某广告位是否存在广告接口
**接口名称**：
hasAd

**方法说明**：
一般用于showAd方法之前，判断当前是否可以展示广告

**方法签名**：
void hasAd(String adSlotId, IKsyunAdExistListener listener);

**参数说明**：
| 名称 | 类型 | 描述|
| ---- | ---- | ---- |
| adSlotId| String |广告位Id|
| listener| IKsyunAdExistListener|判断是否存在广告结果回调|

**回调说明**：

```
public interface IKsyunAdExistListener {
    //该广告位对应广告存在
    void onAdExist();
    //该广告位对应广告不存在
    void onNoAd(int errCode, String errMsg);
}

```

**回调参数说明**：
| 名称 | 类型 | 描述|
| ---- | ---- | ---- |
| errCode| int | 失败时错误码|
| errMsg| String | 失败时错误详情|

####1.4 显示某广告位对应广告接口
**接口名称**：
showAd

**方法签名**：
void showAd(Activity activity, String adSlotId);

**参数说明**：
| 名称 | 类型 | 描述|
| ---- | ---- | ---- |
| activity| Activity |当前所在的activity，用于页面跳转|
| adSlotId| String |当前广告位Id|

####1.5 设置奖励视频结果监听接口
**接口名称**：
setRewardVideoAdListener

**方法签名**：
void setRewardVideoAdListener(IKsyunRewardVideoAdListener rewardVideoAdListener);

**参数说明**：
| 名称 | 类型 | 描述|
| ---- | ---- | ---- |
| rewardVideoAdListener| IKsyunRewardVideoAdListener |奖励视频结果回调接口|

**回调说明**：

```
public interface IKsyunRewardVideoAdListener {
    //奖励视频条件达成时回调
    void onAdAwardSuccess(String adSlotId);
    //奖励视频条件未达成时回调
    void onAdAwardFailed(String adSlotId, int errCode, String errMsg);
}
```

**回调参数说明**：
| 名称 | 类型 | 描述|
| ---- | ---- | ---- |
| adSlotId| String | 当前事件对应的广告位Id|
| errCode| int | 失败时错误码|
| errMsg| String | 失败时错误详情|


####1.6 设置广告事件监听接口
**接口名称**：
setAdListener

**方法签名**：
void setAdListener(IKsyunAdListener listener);

**参数说明**：
| 名称 | 类型 | 描述|
| ---- | ---- | ---- |
| listener| IKsyunAdListener |广告展示事件回调接口|

**回调说明**：

```
public interface IKsyunAdListener {
    //广告展示成功时回调
    void onShowSuccess(String adSlotId);
    //广告展示失败时回调
    void onShowFailed(String adSlotId, int errCode,String errMsg);
    //广告内容播放完成，一般用于视频类广告
    void onADComplete(String adSlotId);
    //广告被点击
    void onADClick(String adSlotId);
    //广告被关闭
    void onADClose(String adSlotId);
}
```

**回调参数说明**：
| 名称 | 类型 | 描述|
| ---- | ---- | ---- |
| adSlotId| String | 当前事件对应的广告位Id|
| errCode| int | 失败时错误码|
| errMsg| String | 失败时错误详情|

####1.7 移除广告事件监听
**接口名称**：
removeAdListener

**方法签名**：
void removeAdListener();

**返回值说明**：
| 类型 | 描述|
| ---- | ---- |
| void |无|

####1.8 移除奖励视频结果监听接口
**接口名称**：
removeRewardVideoAdListener

**方法签名**：
void removeRewardVideoAdListener();

**返回值说明**：
| 类型 | 描述|
| ---- | ---- |
| void |无|

####1.9 移除所有事件监听接口
**接口名称**：
removeAllListener

**方法签名**：
void removeAllListener();

**返回值说明**：
| 类型 | 描述|
| ---- | ---- |
| void |无|

####1.10 获取SDK版本号接口
**接口名称**：
getSdkVersion

**方法签名**：
String getSdkVersion();

**返回值说明**：
| 类型 | 描述|
| ---- | ---- |
| String |SDK当前版本号，eg: "4.0.0"|

####1.11 清除SDK已缓存广告资源
**接口名称**：
clearCache

**方法签名**：
void clearCache();

**返回值说明**：
| 类型 | 描述|
| ---- | ---- |
| void |无|

####1.12 宿主Activity生命周期onResume回调
**接口名称**：
onResume

**方法签名**：
void onResume(Activity activity);

**参数说明**：
| 名称 | 类型 | 描述|
| ---- | ---- | ---- |
| activity| Activity |宿主Activity|

**返回值说明**：
| 类型 | 描述|
| ---- | ---- |
| void |无|

####1.13 宿主Activity生命周期onPause回调
**接口名称**：
onPause

**方法签名**：
void onPause(Activity activity);

**参数说明**：
| 名称 | 类型 | 描述|
| ---- | ---- | ---- |
| activity| Activity |宿主Activity|

**返回值说明**：
| 类型 | 描述|
| ---- | ---- |
| void |无|

####1.14 宿主Activity生命周期onDestroy回调
**接口名称**：
onDestroy

**方法签名**：
void onDestroy(Activity activity);

**参数说明**：
| 名称 | 类型 | 描述|
| ---- | ---- | ---- |
| activity| Activity |宿主Activity|

**返回值说明**：
| 类型 | 描述|
| ---- | ---- |
| void |无|

###2、SDK配置项接口
####2.1 设置SDK请求环境
**接口名称**：
setSdkEnvironment

**方法说明**：

 - SDK分为线上环境、测试环境及开发环境
 - 
 - 请在init方法使用前调用

**方法签名**：
public void setSdkEnvironment(int environment);

**参数说明**：
| 名称 | 类型 | 描述|
| ---- | ---- | ---- |
| environment| int |0开发环境，1测试环境，2线上环境，3沙盒环境|
 
####2.2 设置是否允许奖励视频广告，中途出现关闭按钮
**接口名称**：
setShowCloseBtnOfRewardVideo

**方法说明**：

 - 请在init方法使用前调用

**方法签名**：
void setShowCloseBtnOfRewardVideo(boolean isShow);

**参数说明**：
| 名称 | 类型 | 描述|
| ---- | ---- | ---- |
| isShow| boolean |true允许出现关闭，false不允许|

####2.3 设置奖励视频广告，中途出现关闭按钮时机
**接口名称**：
setCloseBtnComingTimeOfRewardVideo

**方法说明**：

 - 请在init方法使用前调用

**方法签名**：
void setCloseBtnComingTimeOfRewardVideo(int second);

**参数说明**：
| 名称 | 类型 | 描述|
| ---- | ---- | ---- |
| second| int |出现关闭按钮时机，单位为秒|
 
####2.4 设置SDK单个通用配置项接口
**接口名称**：
setSingleConfig

**方法说明**：

 - 请在init方法使用前调用

**方法签名**：
public void setSingleConfig(String key, Object value);

**参数说明**：
| 名称 | 类型 | 描述|
| ---- | ---- | ---- |
| key| String |配置项对应Key，具体可以设置的值，参见附录1|
| value| String |配置项对应取值|

###3、错误码定义
SDK错误码定义如下：

| 错误码 | 描述|
| ---- | ---- |
| 1000 |接口参数错误|
| 1001 |插件加载失败|
| 1002 |SDK未初始化|
| 1003 |SDK内部请求失败|
| 1004 |SDK不支持的广告位|
| 1005 |SDK获取广告位列表信息失败|
| 1006 |该广告位没有对应的广告|
| 1007 |代理Activity未找到|
| 1008 |SDK服务端系统繁忙|
| 1009 |AppId无效|
| 1010 |广告位Id无效|
| 1011 |当前AppId没有任何广告位|
| 1100 |SDK服务端内部错误|
| 1200 |SDK服务端系统错误|
| 2000 |未知错误|



###4、附录
####4.1、附录1 - SDK配置项键值定义
| Key类型 | Key取值|Value类型|Value描述|
| ---- | ---- | ---- |---- |
| String| KeySdkEnv | int|0开发环境，1测试环境，2生产环境，3沙箱环境 |
| String | KeyIsShowClose | boolean | true允许奖励视频中途显示关闭按钮，false不允许 |
| String | KeyCloseComingTime | int | 奖励视频中途显示关闭按钮实际，单位为秒|
| String | KeyEnableSdkRequestPermission | boolean | true允许SDK主动向用户申请动态权限，false不允许 |

####4.2、附录2 - SDK初始化回调Map键值定义
| Key类型 | Key取值|Value类型|Value描述|
| ---- | ---- | ---- |---- |
| String| KeyAdSlots | String|对应初始化appId的所有广告位信息，以JSON数组形式呈现 |
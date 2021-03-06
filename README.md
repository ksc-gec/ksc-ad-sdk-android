# ksc-ad-sdk-android
## 简介
 **金山云移动广告SDK**
## 接入文档
  
  [金山云移动广告Android SDK快速接入文档](https://github.com/ksc-gec/ksc-ad-sdk-android/blob/master/sdk_doc_cn/%E9%87%91%E5%B1%B1%E4%BA%91%E7%A7%BB%E5%8A%A8%E5%B9%BF%E5%91%8AAndroid%20SDK%E5%BF%AB%E9%80%9F%E6%8E%A5%E5%85%A5%E6%96%87%E6%A1%A3%20V4.1.0.pdf) ： 包含接入方式，接入流程，以及相关常用接口的使用方式.

  [金山云移动广告Android SDK接口文档](https://github.com/ksc-gec/ksc-ad-sdk-android/blob/master/sdk_doc_cn/%E9%87%91%E5%B1%B1%E4%BA%91%E7%A7%BB%E5%8A%A8%E5%B9%BF%E5%91%8AAndroid%20SDK%E6%8E%A5%E5%8F%A3%E6%96%87%E6%A1%A3%20V4.1.0.pdf)：所有可用接口的说明文档，包含相关配置项.

  [金山云移动广告Android SDK常见问题FAQ](https://github.com/ksc-gec/ksc-ad-sdk-android/blob/master/sdk_doc_cn/%E9%87%91%E5%B1%B1%E4%BA%91%E7%A7%BB%E5%8A%A8%E5%B9%BF%E5%91%8AAndroid%20SDK%E5%B8%B8%E8%A7%81%E9%97%AE%E9%A2%98FAQ.pdf)：在接入过程中常见的疑问在此文档中都有解答。

  英文版：

  [KingSoft Ksyun Mobile Advertising Android SDK Quick Access Document](https://github.com/ksc-gec/ksc-ad-sdk-android/blob/master/sdk_doc_en/KingSoft%20Ksyun%20Mobile%20Advertising%20Android%20SDK%20Quick%20Access%20Document%20V4.0.3.pdf)

## 版本更新记录
[当前最新版本： 4.1.0](https://github.com/ksc-gec/ksc-ad-sdk-android)

**版本 4.1.0 [2018/11/6]** 
1. 支持开屏广告；
2. 支持展示类的开屏和奖励视频；
3. 修复已知的bug;
4. 修复UA获取不及时问题

**版本 4.0.7 [2018/8/20]** 
1. 修改file_paths名称，改为ksyun_file_paths
2. 添加Tracking事件上报回调接口
3. 降低心跳上报频率
4. 优化demo提示相关项目
5. 修复已知的bug,降低插件apk的size

**版本 4.0.5 [2018/7/3]**
1. 添加部分统计信息维度
2. 新增小米机型有限条件下的静默安装功能，不影响原流程
3. 去除原FileProvider依赖的xml文件中，需要手动填写包名的限制
4. 新增Android 8.0 允许安装未知来源应用权限处理
5. 新增接入流程中测试验证过程的详细说明

**版本 4.0.4 [2018/4/9]**
1. 添加奔溃统计，修复部分Bug
2. 调整奖励视频观看途中退出后的交互逻辑，由直接退出改成展示落地页
3. 新增自动缓存广告位支持，通过setAutoCacheAd(String adSlotId)接口设置，使用后无需客户再手动调用load接口加载，SDK会自动拉取广告

**版本 4.0.3 [2018/3/9]**
1. 修改preloadAd接口，改名为loadAd接口
2. 去除原有的hasLocalAd接口，后续统一使用hasAd接口
3. 去除移动网络下限制加载广告的逻辑，此部分已添加配置项 -- 2018/8/25

**版本 4.0.2 [2018/3/5]**
1. 修复偶现的关闭广告按钮不出现问题
2. 修复部分机型上，Home键退出后，返回APP时奖励视频播放退出问题。
3. 修改FileProvider包名，避免集成其它SDK时发生冲突

**版本 4.0.1 [2018/1/26]**
1. 添加生命周期方法下传接口及清除缓存接口
2. 添加hasLocalAd接口，判断是否存在本地已缓存视频广告
3. 添加2001错误码
4. 添加preload单个广告位接口

**版本 4.0.0 [2017/12/15]**
1. 初版更新

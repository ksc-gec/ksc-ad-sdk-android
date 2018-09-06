package com.ads.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ads.R;
import com.ads.adlist.AdInfo;
import com.ads.utils.ActionBarUtils;
import com.ads.utils.DemoConstants;
import com.ads.utils.PreferencesUtil;
import com.ksc.ad.sdk.IKsyunAdListener;
import com.ksc.ad.sdk.IKsyunAdLoadListener;
import com.ksc.ad.sdk.InterstitialAd;
import com.ksc.ad.sdk.InterstitialAdListener;
import com.ksc.ad.sdk.KsyunAdSdk;

import java.util.Timer;
import java.util.TimerTask;

import static com.ads.utils.DemoConstants.ENIM_HAVE_NO_EFFECTIVE_AD;

/**
 * Company: ksyun;<p/>
 * Author: HeHaoNan;<p/>
 * Date: 2018/1/5,下午2:49;<p/>
 * Package_Name: com.ads.ui;<p/>
 * Description: ;<p/>
 * Other: ;
 */
public class AdInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private ActionBarUtils mActionBarUtils;
    private TextView mAdIdTv;
    private TextView mAdNameTv;
    private TextView mAdTypeTv;
    private TextView mAdStatusTv;
    private Button mPlayTv;
    private TextView mShowInterstitialTv;
    private AdInfo mAdInfo;
    public static final int TIME = 2000;//每两秒查询一次结果
    private Timer mTimer;
    private MyTask mMyTask;
    public static final String VIDEO = "9";
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_info);
        initView();
        initData();
        initInterstitialAd();
    }

    private void initInterstitialAd() {
        if (mAdInfo.adslot_type.equals(DemoConstants.INTERSTITIAL)) {
            final String adslot_id = mAdInfo.adslot_id;

            mInterstitialAd = KsyunAdSdk.getInstance().initInterstitialAd(this, adslot_id,
                    true, new InterstitialAdListener() {
                        @Override
                        public void onInterstitialAdShowSuccess(String adSlotId) {
                            showToast("插屏广告展示成功");
                        }

                        @Override
                        public void onInterstitialAdShowFailed(int i, String adSlotId) {
                            showToast("插屏广告展示失败" + i);
                            if (i == ENIM_HAVE_NO_EFFECTIVE_AD) {
                                showToast("插屏-不存在有效广告");
                            } else {
                                showToast("插屏广告展示失败" + i);
                            }
                        }

                        @Override
                        public void onInterstitialAdShowEnd(String adSlotId) {
                            showToast("插屏广告展示结束");
                        }

                        @Override
                        public void onInterstitialAdClicked(String adSlotId) {
                            showToast("插屏广告展示点击事件");
                        }
                    });
        }
    }

    /**
     *
     */
    private void initData() {
        Intent intent = getIntent();
        intent.setExtrasClassLoader(AdInfo.class.getClassLoader());
        mAdInfo = intent.getParcelableExtra(DemoConstants.KEY_AD_INFO);

        if (null != mAdInfo) {
            judgeAdStatus(mAdInfo.adslot_id);
            mAdIdTv.setText(String.valueOf("广告位ID：" + mAdInfo.adslot_id));
            mAdNameTv.setText(String.valueOf("广告位名称：" + mAdInfo.adslot_name));
            mAdTypeTv.setText(String.valueOf("广告位类型：" + mAdInfo.adslot_type));
            mAdStatusTv.setText(String.valueOf("广告位状态：" + mAdInfo.adslot_status));

            setVisiableView(mAdInfo.adslot_type);
        } else {
            showToast("广告信息为空");
        }

    }

    private void setVisiableView(String adType) {

        if (adType.equals(DemoConstants.INTERSTITIAL)) {
            mShowInterstitialTv.setVisibility(View.VISIBLE);
            mPlayTv.setVisibility(View.GONE);
        } else if (adType.equals(DemoConstants.VIDEO)) {
            mPlayTv.setVisibility(View.VISIBLE);
            mShowInterstitialTv.setVisibility(View.GONE);

        } else if (adType.equals(DemoConstants.SPLASH)) {
            mPlayTv.setVisibility(View.GONE);
            mShowInterstitialTv.setVisibility(View.GONE);
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        mActionBarUtils = new ActionBarUtils(this);
        mActionBarUtils.setBaseActionBarForAdInfo("广告信息", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivity();
            }
        });
        mAdIdTv = findViewById(R.id.item_ad_list_id_tv);
        mAdNameTv = findViewById(R.id.item_ad_list_name_tv);
        mAdTypeTv = findViewById(R.id.item_ad_list_type_tv);
        mAdStatusTv = findViewById(R.id.item_ad_list_status_tv);
        mPlayTv = findViewById(R.id.item_ad_list_play_tv);
        mPlayTv.setAlpha(0.4f);
        mPlayTv.setOnClickListener(this);
        mShowInterstitialTv = findViewById(R.id.item_ad_list_show_interstitial_tv);
        mShowInterstitialTv.setOnClickListener(this);
        setAdListener();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == mPlayTv.getId()) {
            if (mAdInfo.isHasAd) {
                closeActivity();
                playAdVideo(mAdInfo.adslot_id);
            } else {
                showToast("没有广告");
            }
        } else if (id == mShowInterstitialTv.getId()) {
            if (KsyunAdSdk.getInstance().hasAd(mAdInfo.adslot_id)) {
                mInterstitialAd.showAd();
            } else {
                showToast("没有广告");
            }
        }
    }

    private void judgeAdStatus(String adSlotId) {
        boolean isShowLocal = PreferencesUtil.getBoolean(this, DemoConstants.KEY_SDK_SHOW_LOCAL,
                DemoConstants.VALUE_SDK_SHOW_LOCAL);
        hasAd(adSlotId);
    }

    private void hasAd(final String adSlotId) {
        if (KsyunAdSdk.getInstance().hasAd(adSlotId)) {
            adExist();
        } else {
            showToast("本地没有已缓存广告");
            adNotExist(adSlotId);
        }
    }

    private void adExist() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdInfo.isHasAd = true;
                mPlayTv.setAlpha(1.0f);
            }
        });
    }

    private void adNotExist(final String adSlotId) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                //preLoadNextAd(adSlotId); //没有缓存则预加载去掉自动去加载下一个，导致测试自缓存数据不准确
                //创建任务轮询,获取外部和内部预加载值
                startTask();
            }
        });
    }

    private void startTask() {
        mTimer = new Timer(true);
        mMyTask = new MyTask();
        mTimer.schedule(mMyTask, 0, TIME);
    }

    private void playAdVideo(String adSlotId) {
        KsyunAdSdk.getInstance().showAd(this, adSlotId);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (null == mInterstitialAd) {
                closeActivity();
                return false;
            }
            if (mInterstitialAd.isAdShowing()) {
                mInterstitialAd.hideAd();
                return false;
            } else {
                closeActivity();
                return false;
            }
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    /**
     * 奖励视频播放监听
     */
    private void setAdListener() {
        KsyunAdSdk.getInstance().setAdListener(new IKsyunAdListener() {
            @Override
            public void onShowSuccess(String adSlotId) {
                Log.d(DemoConstants.INIT, "onShowSuccess : " + adSlotId);
            }

            @Override
            public void onShowFailed(String adSlotId, int erroCode, String erroMsg) {
                //播放失败,预加载下一个奖励视频
                Log.d(DemoConstants.INIT, "onShowFailed :  adSlotId =" + adSlotId +
                        "，erroCode = " + erroCode + "，erroMsg = " + erroMsg);
                //preLoadNextAd(adSlotId);  去掉loadNext,测试自缓存
            }

            @Override
            public void onADComplete(String adSlotId) {
                //播放成功，预加载下一个奖励视频
                Log.d(DemoConstants.INIT, "onADComplete：" + adSlotId);
                //preLoadNextAd(adSlotId); 去掉loadNext,测试自缓存
            }

            @Override
            public void onADClick(String adSlotId) {
                Log.d(DemoConstants.INIT, "onADClick：" + adSlotId);
            }

            @Override
            public void onADClose(String adSlotId) {
                Log.d(DemoConstants.INIT, "onADClose：" + adSlotId);
            }
        });
    }

    private void preLoadNextAd(String adSlotId) {
        KsyunAdSdk.getInstance().loadAd(adSlotId, new IKsyunAdLoadListener() {
            @Override
            public void onAdInfoSuccess() {
                Log.d(DemoConstants.INIT, "onAdInfoSuccess");
            }

            @Override
            public void onAdInfoFailed(int errCode, String errMsg) {
                Log.d(DemoConstants.INIT, "onAdInfoFailed：erroCode = " + errCode + "，errMsg" + errMsg);
            }

            @Override
            public void onAdLoaded(String adSlotId) {
                Log.d(DemoConstants.INIT, "onAdLoaded：" + adSlotId);
            }
        });
    }

    public class MyTask extends TimerTask {

        @Override
        public void run() {
            if (KsyunAdSdk.getInstance().hasAd(mAdInfo.adslot_id)) {
                stopTimer();
                mAdInfo.isHasAd = true;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mPlayTv.setAlpha(1.0f);
                    }
                });
            }
        }
    }

    /**
     * 停止任务
     */
    private void stopTimer() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        } else {
            Log.e(DemoConstants.INIT, "mTimer = null");
        }
    }

    /**
     * 关闭页面
     */
    public void closeActivity() {
        stopTimer();
        finish();
    }
}

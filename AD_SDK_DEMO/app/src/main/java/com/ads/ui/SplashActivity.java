package com.ads.ui;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ads.R;
import com.ads.utils.DemoConstants;
import com.ads.utils.PreferencesUtil;
import com.ksc.ad.sdk.KsyunAdSdk;
import com.ksc.ad.sdk.SplashAd;
import com.ksc.ad.sdk.SplashAdListener;

import static com.ads.utils.DemoConstants.ENIM_HAVE_NO_EFFECTIVE_AD;

/**
 * Company: ksyun;<p/>
 * Author: Hhn;<p/>
 * Date: 2018/3/21,下午5:37;<p/>
 * Package_Name: com.ksc.ad.demo.ui;<p/>
 * Description: ;<p/>
 * Other: ;
 */
public class SplashActivity extends AppCompatActivity implements SplashAdListener {

    private RelativeLayout mContentRl;
    private SplashAd mSplashAd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        initKsyunSplashView();
    }

    private void initView() {
        mContentRl = findViewById(R.id.ac_splash_content_rl);
    }

    private void initKsyunSplashView() {
        //此处的adslot id 是通过在设置页面动态添加
        String adslotId = PreferencesUtil.getString(this, DemoConstants.KEY_SDK_SPLASH_SLOT_ID,
                DemoConstants.VALUE_ADSLOT_ID);
        mSplashAd = KsyunAdSdk.getInstance().initSplashAd(SplashActivity.this, mContentRl, adslotId,
                6, true, SplashActivity.this);
        if (null != mSplashAd) {
            mSplashAd.showAd();
        } else {
            showToast("mSplashAd == null");
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSplashAdShowSuccess(String adSlotId) {
        showToast("开屏展示成功");
    }

    @Override
    public void onSplashAdShowEnd(String adSlotId) {
        //showToast("开屏展示结束");
        //开屏展示结束
        jumpToMainActivity();
        finish();
    }

    @Override
    public void onSplashAdShowFailed(int errorCode, String adSlotId) {
        if (errorCode == ENIM_HAVE_NO_EFFECTIVE_AD) {
            showToast("开屏-不存在有效广告");
        } else {
            showToast("errro code" + errorCode);
        }
        jumpToMainActivity();
        finish();
    }

    @Override
    public void onSplashAdClicked(String adSlotId) {
        showToast("广告被点击");
    }

    private void jumpToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            //切换到竖屏 修改布局文件
            setContentView(R.layout.activity_splash);
        } else {
            //切换到横屏 修改布局文件
            setContentView(R.layout.activity_splash);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return keyCode != KeyEvent.KEYCODE_BACK && super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mSplashAd) {
            mSplashAd.onDestroy();
        }
    }
}

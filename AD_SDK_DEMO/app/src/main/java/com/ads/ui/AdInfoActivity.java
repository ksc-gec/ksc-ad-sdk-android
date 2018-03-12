package com.ads.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ads.R;
import com.ads.adlist.AdInfo;
import com.ads.utils.ActionBarUtils;
import com.ads.utils.DemoConstants;
import com.ksc.ad.sdk.IKsyunAdListener;
import com.ksc.ad.sdk.IKsyunAdLoadListener;
import com.ksc.ad.sdk.KsyunAdSdk;

/**
 * Company: ksyun;<p/>
 * Author: HeHaoNan;<p/>
 * Date: 2018/1/5,下午2:49;<p/>
 * Package_Name: com.ksc.ad.demo.ui;<p/>
 * Description: ;<p/>
 * Other: ;
 */
public class AdInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private ActionBarUtils mActionBarUtils;
    private TextView mAdIdTv;
    private TextView mAdNameTv;
    private TextView mAdTypeTv;
    private TextView mAdStatusTv;
    private TextView mPlayTv;
    private AdInfo mAdInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_info);
        initView();
        initData();

    }

    private void initData() {
        Intent intent = getIntent();
        intent.setExtrasClassLoader(AdInfo.class.getClassLoader());
        mAdInfo = intent.getParcelableExtra(DemoConstants.KEY_AD_INFO);

        if (null != mAdInfo) {
            hasAd(mAdInfo.adslot_id);
            mAdIdTv.setText(String.valueOf("AD ID：" + mAdInfo.adslot_id));
            mAdNameTv.setText(String.valueOf("AD NAME：" + mAdInfo.adslot_name));
            mAdTypeTv.setText(String.valueOf("AD TYPE：" + mAdInfo.adslot_type));
            mAdStatusTv.setText(String.valueOf("AD STATUS：" + mAdInfo.adslot_status));
        } else {
            showToast("AD info is empty");
        }

    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        mActionBarUtils = new ActionBarUtils(this);
        mActionBarUtils.setBaseActionBar("AD INFO");
        mAdIdTv = findViewById(R.id.item_ad_list_id_tv);
        mAdNameTv = findViewById(R.id.item_ad_list_name_tv);
        mAdTypeTv = findViewById(R.id.item_ad_list_type_tv);
        mAdStatusTv = findViewById(R.id.item_ad_list_status_tv);
        mPlayTv = findViewById(R.id.item_ad_list_play_tv);
        mPlayTv.setAlpha(0.4f);
        mPlayTv.setOnClickListener(this);
        setAdListener();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == mPlayTv.getId()) {
            if (mAdInfo.isHasAd) {
                finish();
                playAdVideo(mAdInfo.adslot_id);
            } else {
                showToast("have no ad");
            }
        }
    }

    private void hasAd(final String adslot_id) {
        //                if (KsyunAdSdk.getInstance().hasAd(adslot_id)) {
        //                    mAdInfo.isHasAd = true;
        //                    mPlayTv.setAlpha(1.0f);
        //                    if (mIsOneStep) {
        //                        playAdVideo(mAdInfo.adslot_id);
        //                    }
        //                } else {
        //                    runOnUiThread(new Runnable() {
        //                        @Override
        //                        public void run() {
        //                            showToast("没有广告");
        //        preLoadNextAd(adslot_id);
        //                        }
        //                    });
        //                }
        if (KsyunAdSdk.getInstance().hasAd(adslot_id)) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mAdInfo.isHasAd = true;
                    mPlayTv.setAlpha(1.0f);
                        finish();
                        playAdVideo(mAdInfo.adslot_id);
                }
            });
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showToast("No cached ads in local");
                    preLoadNextAd(adslot_id);
                }
            });
        }

    }

    private void playAdVideo(String adSlotId) {
        KsyunAdSdk.getInstance().showAd(this, adSlotId);
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
                preLoadNextAd(adSlotId);
            }

            @Override
            public void onADComplete(String adSlotId) {
                //播放成功，预加载下一个奖励视频
                Log.d(DemoConstants.INIT, "onADComplete：" + adSlotId);
                preLoadNextAd(adSlotId);
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
            public void onAdInfoFailed(int erroCode, String erroMsg) {
                Log.d(DemoConstants.INIT, "onAdInfoFailed：erroCode = " + erroCode + "，erroMsg" + erroMsg);
            }

            @Override
            public void onAdLoaded(String adSlotId) {
                Log.d(DemoConstants.INIT, "onAdLoaded：" + adSlotId);
            }
        });
    }

}

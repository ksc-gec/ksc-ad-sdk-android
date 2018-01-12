package com.ads.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ads.R;
import com.ads.adlist.AdInfo;
import com.ads.utils.ActionBarUtils;
import com.ads.utils.DemoConstants;
import com.ksc.ad.sdk.IKsyunAdExistListener;
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
        mAdInfo = getIntent().getParcelableExtra(DemoConstants.KEY_AD_INFO);
        if (null != mAdInfo) {
            hasAd(mAdInfo.adslot_id);
            mAdIdTv.setText(String.valueOf("广告位ID：" + mAdInfo.adslot_id));
            mAdNameTv.setText(String.valueOf("广告位名称：" + mAdInfo.adslot_name));
            mAdTypeTv.setText(String.valueOf("广告位类型：" + mAdInfo.adslot_type));
            mAdStatusTv.setText(String.valueOf("广告位状态：" + mAdInfo.adslot_status));
        } else {
            showToast("广告信息为空");
        }

    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        mActionBarUtils = new ActionBarUtils(this);
        mActionBarUtils.setBaseActionBar("广告信息");
        mAdIdTv = findViewById(R.id.item_ad_list_id_tv);
        mAdNameTv = findViewById(R.id.item_ad_list_name_tv);
        mAdTypeTv = findViewById(R.id.item_ad_list_type_tv);
        mAdStatusTv = findViewById(R.id.item_ad_list_status_tv);
        mPlayTv = findViewById(R.id.item_ad_list_play_tv);
        mPlayTv.setAlpha(0.4f);
        mPlayTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == mPlayTv.getId()) {
            if (mAdInfo.isHasAd) {
                playAdVideo(mAdInfo.adslot_id);
            } else {
                showToast("没有广告");
            }
        }
    }

    private void hasAd(String adslot_id) {
        KsyunAdSdk.getInstance().hasAd(adslot_id, new IKsyunAdExistListener() {

            @Override
            public void onAdExist() {
                        mAdInfo.isHasAd = true;
                        mPlayTv.setAlpha(1.0f);
            }

            @Override
            public void onNoAd(final int errCode, final String errMsg) {
                        showToast("没有广告" + errCode + errMsg);
            }
        });
    }

    private void playAdVideo(String adSlotId) {
        KsyunAdSdk.getInstance().showAd(this, adSlotId);
    }

}

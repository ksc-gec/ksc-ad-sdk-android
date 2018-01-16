package com.ads.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ads.R;
import com.ads.adlist.AdInfo;
import com.ads.adlist.AdListAdapter;
import com.ads.ui.statis.StatisticInfoActivity;
import com.ads.utils.ActionBarUtils;
import com.ads.utils.DemoConstants;
import com.ads.utils.PreferencesUtil;
import com.ksc.ad.sdk.IKsyunAdInitResultListener;
import com.ksc.ad.sdk.IKsyunAdPreloadListener;
import com.ksc.ad.sdk.KsyunAdSdk;
import com.ksc.ad.sdk.KsyunAdSdkConfig;
import com.ksc.ad.sdk.util.KsyunSdkConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdListAdapter.AdInfoClickListener {

    //应用Id
    public static final String APP_ID = "a4d2ff0b";
    private ActionBarUtils mActionBarUtils;
    private TextView mAppIdTv;
    private TextView mInitTv;
    private TextView mInitStatusTv;
    private TextView mPreLoadTv;
    private TextView mPreLoadStatusTv;
    private RecyclerView mAdListRlv;
    private AdListAdapter mAdListAdapter;
    private List<AdInfo> mAdInfoList = new ArrayList<>();
    private boolean isInitSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mActionBarUtils = new ActionBarUtils(this);
        mActionBarUtils.setMainAcActionBarView(this);
        mAppIdTv = findViewById(R.id.main_app_id_tv);
        mInitTv = findViewById(R.id.main_init_sdk_tv);
        mInitStatusTv = findViewById(R.id.main_init_sdk_status_tv);
        mPreLoadTv = findViewById(R.id.main_pre_load_tv);
        mPreLoadStatusTv = findViewById(R.id.main_pre_load_status_tv);

        mInitTv.setOnClickListener(this);
        mPreLoadTv.setOnClickListener(this);
        mAdListRlv = findViewById(R.id.main_ad_list_rlv);
        mAdListRlv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mAdListAdapter = new AdListAdapter(mAdInfoList);
        mAdListAdapter.setClickListener(this);
        mAdListRlv.setAdapter(mAdListAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAppIdTv.setText(String.valueOf("AppId：" + PreferencesUtil.getString(this, DemoConstants
                .KEY_APP_ID, APP_ID)));
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == mActionBarUtils.getLeftTv().getId()) {
            Intent intent = new Intent(getApplicationContext(), SetupActivity.class);
            startActivity(intent);
        } else if (id == mActionBarUtils.getRightTv().getId()) {
            if (isInitSuccess) {
                Intent intent = new Intent(getApplicationContext(), StatisticInfoActivity.class);
                startActivity(intent);
            } else {
                showToast("请先初始化SDK");
            }
        } else if (id == mInitTv.getId()) {
            initSdk();
        } else if (id == mPreLoadTv.getId()) {
            if (isInitSuccess) {
                preLoadAd();
            } else {
                showToast("请先初始化SDK");
            }
        }
    }

    /**
     * 初始化sdk
     */
    private void initSdk() {
        KsyunAdSdkConfig config = new KsyunAdSdkConfig();

        int env = PreferencesUtil.getInt(this, DemoConstants.KEY_SDK_ENV, KsyunAdSdkConfig.TEST_ENV);
        boolean isShow = PreferencesUtil.getBoolean(this, DemoConstants.KEY_SDK_SHOW_CLOSE, true);
        int second = PreferencesUtil.getInt(this, DemoConstants.KEY_SDK_SHOW_TIME, 5);
        String appId = PreferencesUtil.getString(this, DemoConstants.KEY_APP_ID, APP_ID);

        config.setSdkEnvironment(env);
        config.setShowCloseBtnOfRewardVideo(isShow);
        config.setCloseBtnComingTimeOfRewardVideo(second);
        config.setSingleConfig("testKey", "testValue");
        KsyunAdSdk.getInstance().init(MainActivity.this, appId, "channelId", config, new
                IKsyunAdInitResultListener() {
                    @Override
                    public void onSuccess(final Map<String, String> map) {
                        setInitStatus("初始化成功");
                        isInitSuccess = true;
                        String result = map.get(KsyunSdkConstants.KEY_INIT_RESULT_AD_SLOTS);
                        Log.d(DemoConstants.INIT, "id list " + result);
                        if (!TextUtils.isEmpty(result)) {
                            showToast("广告位获取成功");
                            handleAdListJson(result);
                            mAdListAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(final int errCode, final String errMsg) {
                        showToast("初始化失败，errCode：" + errCode + "，errMsg：" + errMsg);
                        setInitStatus("初始化失败：" + errCode + "\n" + errMsg);
                        isInitSuccess = false;
                    }
                });
    }

    private void setInitStatus(String msg) {
        mInitStatusTv.setVisibility(View.VISIBLE);
        mInitStatusTv.setText(msg);
    }

    private void setPreLoadStatus(String msg) {
        mPreLoadStatusTv.setVisibility(View.VISIBLE);
        mPreLoadStatusTv.setText(msg);
    }

    private void handleAdListJson(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                final AdInfo adInfo = new AdInfo();
                JSONObject object = jsonArray.getJSONObject(i);
                adInfo.adslot_id = object.getString("adslot_id");
                adInfo.adslot_name = object.getString("adslot_name");
                adInfo.adslot_type = object.getString("adslot_type");
                adInfo.adslot_status = object.getString("adslot_status");
                mAdInfoList.add(adInfo);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAdPlayClicked(int position, AdInfo adInfo) {
        Intent intent = new Intent(this, AdInfoActivity.class);
        intent.putExtra(DemoConstants.KEY_AD_INFO, adInfo);
        startActivity(intent);
    }

    /**
     * 预加载
     */
    private void preLoadAd() {
        KsyunAdSdk.getInstance().preloadAd(new IKsyunAdPreloadListener() {
            @Override
            public void onAdInfoSuccess() {
                Log.d(DemoConstants.INIT, "onAdInfoSuccess: ");
                setPreLoadStatus("加载广告信息成功");
            }

            @Override
            public void onAdInfoFailed(final int errCode, final String errMsg) {
                Log.d(DemoConstants.INIT, "onAdInfoFailed: " + errCode + "," + errMsg);
                setPreLoadStatus("预加载失败:" + errCode + "\n" + errMsg);
            }

            @Override
            public void onAdLoaded(String adSlotId) {
                Log.d(DemoConstants.INIT, "onAdLoaded: " + adSlotId);
                for (AdInfo adInfo : mAdInfoList) {
                    if (adInfo.adslot_id.equals(adSlotId)) {
                        adInfo.isHasAd = true;
                    }
                }
                mAdListAdapter.notifyDataSetChanged();
            }
        });


    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            KsyunAdSdk.getInstance().resetSdk(this);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}

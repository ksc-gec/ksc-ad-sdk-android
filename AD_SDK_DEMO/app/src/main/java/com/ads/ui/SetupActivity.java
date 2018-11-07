package com.ads.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.ads.R;
import com.ads.utils.ActionBarUtils;
import com.ads.utils.DemoConstants;
import com.ads.utils.PreferencesUtil;
import com.ksc.ad.sdk.KsyunAdSdk;
import com.ksc.ad.sdk.KsyunAdSdkConfig;

/**
 * Company: ksyun;<p/>
 * Author: HeHaoNan;<p/>
 * Date: 2017/12/28,下午5:32;<p/>
 * Package_Name: com.ksc.ad.demo;<p/>
 * Description: ;<p/>
 * Other: ;
 */
public class SetupActivity extends AppCompatActivity implements View.OnClickListener {

    private ActionBarUtils mActionBarUtils;
    private TextView mClearCacheTv;

    private ArrayAdapter<String> mArrayAdapter;
    private EditText mAppIdEt;
    private Spinner mEnvSpinner;
    private Switch mIsShowSwitch;
    private EditText mShowTimeEt;
    private EditText mSplashSlotId;
    private Switch mMobileLoadSwitch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        initView();
        initData();
    }

    private void initView() {
        mActionBarUtils = new ActionBarUtils(this);
        mActionBarUtils.setSetupActionBar("SET", "SAVE", this);
        mAppIdEt = findViewById(R.id.setup_app_id_et);
        mEnvSpinner = findViewById(R.id.setup_env_spn);
        mIsShowSwitch = findViewById(R.id.setup_close_swh);
        mShowTimeEt = findViewById(R.id.setup_show_second_et);
        mClearCacheTv = findViewById(R.id.ac_setup_clear_cache_tv);
        mMobileLoadSwitch = findViewById(R.id.setup_load_4g);
        mSplashSlotId = findViewById(R.id.setup_Splash_slotId_et);
        mClearCacheTv.setOnClickListener(this);
        mArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                DemoConstants.envArray);
        mArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mEnvSpinner.setAdapter(mArrayAdapter);
    }

    private void initData() {
        int temp = PreferencesUtil.getInt(this, DemoConstants.KEY_SDK_ENV, 0);
        if (temp == KsyunAdSdkConfig.RELEASE_ENV) {
            temp = 1;
        } else {
            temp = 0;
        }
        mEnvSpinner.setSelection(temp);

        mAppIdEt.setHint(String.valueOf(PreferencesUtil.getString(this, DemoConstants.KEY_APP_ID,
                DemoConstants.VALUE_APP_ID)));
        mIsShowSwitch.setChecked(PreferencesUtil.getBoolean(this, DemoConstants.KEY_SDK_SHOW_CLOSE,
                DemoConstants.VALUE_SDK_SHOW_CLOSE));
        mShowTimeEt.setHint(String.valueOf(PreferencesUtil.getInt(this, DemoConstants.KEY_SDK_SHOW_CLOSE_TIME,
                DemoConstants.VALUE_SDK_SHOW_TIME)));
        mMobileLoadSwitch.setChecked(PreferencesUtil.getBoolean(this, DemoConstants
                        .KEY_SDK_CLOSE_MOBILE_LOAD,
                DemoConstants.VALUE_SDK_CLOSE_MOBILE_LOAD));
        mSplashSlotId.setHint(String.valueOf(PreferencesUtil.getString(this, DemoConstants.KEY_SDK_SPLASH_SLOT_ID,
                "")));

    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == mActionBarUtils.getRightTv().getId()) {
            if (mEnvSpinner.getSelectedItemPosition() == 0) {
                PreferencesUtil.putInt(this, DemoConstants.KEY_SDK_ENV, KsyunAdSdkConfig.SANDBOX_ENV);
            } else {
                PreferencesUtil.putInt(this, DemoConstants.KEY_SDK_ENV, KsyunAdSdkConfig.RELEASE_ENV);
            }

            PreferencesUtil.putBoolean(this, DemoConstants.KEY_SDK_SHOW_CLOSE,
                    mIsShowSwitch.isChecked());

            if (!TextUtils.isEmpty(mShowTimeEt.getText())) {
                int temp = Integer.valueOf(mShowTimeEt.getText().toString());
                PreferencesUtil.putInt(this, DemoConstants.KEY_SDK_SHOW_CLOSE_TIME, temp);
            }

            String appId = mAppIdEt.getText().toString();
            if (!TextUtils.isEmpty(appId)) {
                PreferencesUtil.putString(this, DemoConstants.KEY_APP_ID, appId);
            }

            PreferencesUtil.putBoolean(this, DemoConstants.KEY_SDK_CLOSE_MOBILE_LOAD,
                    mMobileLoadSwitch.isChecked());

            String splashAdSlotId = mSplashSlotId.getText().toString();
            if (!TextUtils.isEmpty(splashAdSlotId)) {
                PreferencesUtil.putString(this, DemoConstants.KEY_SDK_SPLASH_SLOT_ID, splashAdSlotId.trim());
            }
            showToast("配置保存成功");
            finish();
        } else if (id == mClearCacheTv.getId()) {
            KsyunAdSdk.getInstance().clearCache();
            showToast("清理操作已执行");
        }
    }
}

package com.ads.ui.statis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ads.R;
import com.ads.utils.ActionBarUtils;

/**
 * Company: ksyun;<p/>
 * Author: HeHaoNan;<p/>
 * Date: 2017/12/28,下午5:32;<p/>
 * Package_Name: com.ksc.ad.demo;<p/>
 * Description: ;<p/>
 * Other: ;
 */
public class StatisticInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private ActionBarUtils mActionBarUtils;
    private RelativeLayout mDeviceInfoRl;
    private RelativeLayout mEventNoteRl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic_info);
        initView();
    }

    private void initView() {
        mActionBarUtils = new ActionBarUtils(this);
        mActionBarUtils.setBaseActionBar("statistical information");
        mDeviceInfoRl = findViewById(R.id.ac_statistic_device_info_rl);
        mEventNoteRl = findViewById(R.id.ac_statistic_event_note_rl);
        mDeviceInfoRl.setOnClickListener(this);
        mEventNoteRl.setOnClickListener(this);
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == mActionBarUtils.getLeftTv().getId()) {
            finish();
        } else if (id == mDeviceInfoRl.getId()) {
            Intent intent = new Intent(getApplicationContext(), DeviceInfoActivity.class);
            startActivity(intent);
        } else if (id == mEventNoteRl.getId()) {
            Intent intent = new Intent(getApplicationContext(), EventLogActivity.class);
            startActivity(intent);
        }
    }
}

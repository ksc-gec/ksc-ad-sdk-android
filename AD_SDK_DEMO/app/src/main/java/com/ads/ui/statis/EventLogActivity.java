package com.ads.ui.statis;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

import com.ads.R;
import com.ads.utils.ActionBarUtils;
import com.ksc.ad.sdk.util.KsyunFileUtils;

import java.util.Collections;
import java.util.List;

/**
 * Company: ksyun;<p/>
 * Author: HeHaoNan;<p/>
 * Date: 2018/1/2,下午4:19;<p/>
 * Package_Name: com.ksc.ad.demo.ui;<p/>
 * Description: ;<p/>
 * Other: ;
 */
public class EventLogActivity extends AppCompatActivity {

    private ActionBarUtils mActionBarUtils;
    private TextView mEventLogInfoTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_log);
        initView();
        MyAsyncTask task = new MyAsyncTask();
        task.execute();
    }

    private void initView() {
        mActionBarUtils = new ActionBarUtils(this);
        mActionBarUtils.setBaseActionBar("Behavior log");
        mEventLogInfoTv = findViewById(R.id.event_log_data_tv);
    }

    private class MyAsyncTask extends AsyncTask<String, Integer, List<String>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(List<String> data) {
            super.onPostExecute(data);
            if (null != data && !data.isEmpty()) {
                StringBuilder builder = new StringBuilder();
                for (String str : data) {
                    if (!TextUtils.isEmpty(str)) {
                        builder.append(str).append("\r\n");
                    }
                }
                mEventLogInfoTv.setText(builder);
            } else {
                mEventLogInfoTv.setText("Behavior log is empty");
            }
        }

        @Override
        protected List<String> doInBackground(String... strings) {
            List<String> data = KsyunFileUtils.getStatisInfoData(getApplicationContext());
            if (null != data && !data.isEmpty()) {
                Collections.reverse(data);
            }
            return data;
        }
    }

}

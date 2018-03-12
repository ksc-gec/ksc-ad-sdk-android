package com.ads.utils;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.ads.R;


/**
 * Company: ksyun;<p/>
 * Author: HeHaoNan;<p/>
 * Date: 2017/12/28,下午5:06;<p/>
 * Package_Name: com.ksc.ad.demo.utils;<p/>
 * Description: ;<p/>
 * Other: ;
 */
public class ActionBarUtils {

    private Activity mActivity;
    private TextView mLeftTv;
    private TextView mTitleTv;
    private TextView mRightTv;

    public ActionBarUtils(Activity activity) {
        mActivity = activity;
        initView();
    }

    private void initView() {
        mLeftTv = mActivity.findViewById(R.id.action_bar_left_tv);
        mTitleTv = mActivity.findViewById(R.id.action_bar_title_tv);
        mRightTv = mActivity.findViewById(R.id.action_bar_right_tv);
    }

    public TextView getLeftTv() {
        return mLeftTv;
    }

    public TextView getTitleTv() {
        return mTitleTv;
    }

    public TextView getRightTv() {
        return mRightTv;
    }

    public void setMainAcActionBarView(View.OnClickListener onClickListener) {
        mTitleTv.setText(mActivity.getString(R.string.app_name_ksy));
        mLeftTv.setBackgroundResource(R.mipmap.ksc_icon_setting);
        mRightTv.setBackgroundResource(R.mipmap.ksc_icon_info);
        mLeftTv.setOnClickListener(onClickListener);
        mRightTv.setOnClickListener(onClickListener);
    }

    public void setBaseActionBar(String title) {
        mTitleTv.setText(title);
        mLeftTv.setBackgroundResource(R.mipmap.ksc_icon_back);
        mLeftTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
            }
        });
    }

    public void setSetupActionBar(String title, String rightTxt, View.OnClickListener onClickListener) {
        mTitleTv.setText(title);
        mLeftTv.setBackgroundResource(R.mipmap.ksc_icon_back);
        mRightTv.setText(rightTxt);
        mRightTv.setOnClickListener(onClickListener);
        mLeftTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
            }
        });
    }

}

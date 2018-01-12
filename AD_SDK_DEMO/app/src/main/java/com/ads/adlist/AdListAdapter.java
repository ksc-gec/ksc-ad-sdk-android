package com.ads.adlist;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ads.R;
import com.ads.utils.DemoConstants;

import java.util.List;

/**
 * Company: ksyun;<p/>
 * Author: HeHaoNan;<p/>
 * Date: 2017/12/27,下午7:15;<p/>
 * Package_Name: com.ksc.ad.demo.adlist;<p/>
 * Description: ;<p/>
 * Other: ;
 */
public class AdListAdapter extends RecyclerView.Adapter {

    private List<AdInfo> mAdInfoList;
    private AdInfoClickListener mClickListener;

    public AdListAdapter(List<AdInfo> adInfoList) {
        mAdInfoList = adInfoList;
    }

    public interface AdInfoClickListener {
        void onAdPlayClicked(int position, AdInfo adInfo);
    }

    public void setClickListener(AdInfoClickListener clickListener) {
        mClickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return null == mAdInfoList ? 0 : mAdInfoList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View parentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ad_list, parent,
                false);
        return new AdInfoViewHolder(parentView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AdInfoViewHolder) {
            setAdInfo((AdInfoViewHolder) holder, position);
        }
    }

    private void setAdInfo(AdInfoViewHolder viewHolder, final int position) {
        final AdInfo adInfo = mAdInfoList.get(position);
        viewHolder.mAdIdTv.setText(String.valueOf("广告位ID：" + adInfo.adslot_id));
        viewHolder.mAdNameTv.setText(String.valueOf("广告位名称：" + adInfo.adslot_name));
        viewHolder.mAdTypeTv.setText(String.valueOf("广告位类型：" + adInfo.adslot_type));
        viewHolder.mAdStatusTv.setText(String.valueOf("广告位状态：" + adInfo.adslot_status));

        viewHolder.mJumpIv.setVisibility(View.VISIBLE);
        viewHolder.mPlayTv.setVisibility(View.GONE);
        viewHolder.mParentRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mClickListener) {
                    mClickListener.onAdPlayClicked(position, adInfo);
                } else {
                    Log.e(DemoConstants.INIT, "onClick: mClickListener is null");
                }
            }
        });
    }

    private class AdInfoViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout mParentRl;
        private TextView mAdIdTv;
        private TextView mAdNameTv;
        private TextView mAdTypeTv;
        private TextView mAdStatusTv;
        private TextView mPlayTv;
        private ImageView mJumpIv;

        public AdInfoViewHolder(View itemView) {
            super(itemView);
            mParentRl = itemView.findViewById(R.id.item_ad_list_rl);
            mAdIdTv = itemView.findViewById(R.id.item_ad_list_id_tv);
            mAdNameTv = itemView.findViewById(R.id.item_ad_list_name_tv);
            mAdTypeTv = itemView.findViewById(R.id.item_ad_list_type_tv);
            mAdStatusTv = itemView.findViewById(R.id.item_ad_list_status_tv);
            mPlayTv = itemView.findViewById(R.id.item_ad_list_play_tv);
            mJumpIv = itemView.findViewById(R.id.item_ad_list_play_jump_iv);
        }

    }

}

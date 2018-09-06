package com.ads.adlist;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ads.R;
import com.ads.utils.DemoConstants;

import java.util.List;

import static com.ads.utils.DemoConstants.ENUM_AD_SLOT_DEFAULT;
import static com.ads.utils.DemoConstants.ENUM_AD_SLOT_DELIVERING;
import static com.ads.utils.DemoConstants.ENUM_AD_SLOT_NOT_DELIVER;
import static com.ads.utils.DemoConstants.INTERSTITIAL;
import static com.ads.utils.DemoConstants.SPLASH;
import static com.ads.utils.DemoConstants.VIDEO;

/**
 * Company: ksyun;<p/>
 * Author: HeHaoNan;<p/>
 * Date: 2017/12/27,下午7:15;<p/>
 * Package_Name: com.ads.adlist;<p/>
 * Description: ;<p/>
 * Other: ;
 */
public class AdListAdapter extends RecyclerView.Adapter {

    private List<AdInfo> mAdInfoList;
    private AdInfoClickListener mClickListener;
    private Context mContext;

    public AdListAdapter(Context context, List<AdInfo> adInfoList) {
        mContext = context;
        mAdInfoList = adInfoList;
    }

    public interface AdInfoClickListener {
        void onAdPlayClicked(int position, AdInfo adInfo);

        void onAdSlotSetAutoCache(int position, AdInfo adinfo);

        void onLoadAd(int position, String adSlotId);
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
        viewHolder.mAdTypeTv.setText(String.valueOf("广告位类型：" + getTextByType(adInfo)));
        viewHolder.mAdStatusTv.setText(getStatusByType(adInfo));

        viewHolder.mLinear.setVisibility(View.VISIBLE);
        viewHolder.mPlayTv.setVisibility(View.GONE);
        viewHolder.mParentRl.setBackgroundColor(getColorByType(adInfo));
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

        viewHolder.mSingleLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mClickListener) {
                    mClickListener.onLoadAd(position, adInfo.adslot_id);
                } else {
                    Log.e(DemoConstants.INIT, "onClick: mClickListener is null");
                }
            }
        });

        //        if (adInfo.adslot_type.equals(VIDEO)) {
        //            viewHolder.mAutoCache.setVisibility(View.VISIBLE);
        //            viewHolder.mAutoCache.setOnClickListener(new View.OnClickListener() {
        //                @Override
        //                public void onClick(View v) {
        //                    if (null != mClickListener) {
        //                        mClickListener.onAdSlotSetAutoCache(position, adInfo);
        //                        v.setVisibility(View.GONE);
        //                    } else {
        //                        Log.e(DemoConstants.INIT, "onClick: mClickListener is null");
        //                    }
        //                }
        //            });
        //
        //        } else {
        //            viewHolder.mAutoCache.setVisibility(View.GONE);
        //        }

    }

    private int getColorByType(AdInfo adInfo) {
        switch (adInfo.adslot_type) {
            case VIDEO:
                return mContext.getResources().getColor(R.color.ksyun_color_black_30);
            case SPLASH:
                return mContext.getResources().getColor(R.color.color_FF7900);
            case INTERSTITIAL:
                return mContext.getResources().getColor(R.color.color_6CF862);
            default:
                return mContext.getResources().getColor(R.color.ksyun_color_black_30);
        }
    }

    private String getTextByType(AdInfo adInfo) {
        switch (adInfo.adslot_type) {
            case VIDEO:
                return "视频";
            case SPLASH:
                return "开屏";
            case INTERSTITIAL:
                return "插屏";
            default:
                return "未知";
        }
    }

    private Spannable getStatusByType(AdInfo adInfo) {
        String status = "广告位状态：";
        switch (adInfo.adslot_status) {
            case ENUM_AD_SLOT_DEFAULT:
                status += "未知";
                break;
            case ENUM_AD_SLOT_DELIVERING:
                status += "上线";
                break;
            case ENUM_AD_SLOT_NOT_DELIVER:
                status += "下线";
                break;
            default:
                status += "未知";
        }

        Spannable spannable = new SpannableString(status);
        spannable.setSpan(new ForegroundColorSpan(Color.RED), 6, spannable.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }

    private class AdInfoViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout mParentRl;
        private TextView mAdIdTv;
        private TextView mAdNameTv;
        private TextView mAdTypeTv;
        private TextView mAdStatusTv;
        private Button mPlayTv;
        private LinearLayout mLinear;
        private TextView mAutoCache;
        private TextView mSingleLoad;

        public AdInfoViewHolder(View itemView) {
            super(itemView);
            mParentRl = itemView.findViewById(R.id.item_ad_list_rl);
            mAdIdTv = itemView.findViewById(R.id.item_ad_list_id_tv);
            mAdNameTv = itemView.findViewById(R.id.item_ad_list_name_tv);
            mAdTypeTv = itemView.findViewById(R.id.item_ad_list_type_tv);
            mAdStatusTv = itemView.findViewById(R.id.item_ad_list_status_tv);
            mPlayTv = itemView.findViewById(R.id.item_ad_list_play_tv);
            mLinear = itemView.findViewById(R.id.item_ad_list_play_ll);
            mAutoCache = itemView.findViewById(R.id.item_ad_set_auto_cache);
            mSingleLoad = itemView.findViewById(R.id.item_load_single_ad);
        }

    }

}

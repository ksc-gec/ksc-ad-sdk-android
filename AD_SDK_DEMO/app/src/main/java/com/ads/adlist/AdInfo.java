package com.ads.adlist;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Company: ksyun;<p/>
 * Author: HeHaoNan;<p/>
 * Date: 2017/12/27,下午7:20;<p/>
 * Package_Name: com.ksc.ad.demo.adlist;<p/>
 * Description: ;<p/>
 * Other: ;
 */
public class AdInfo implements Parcelable {

    public String adslot_id;
    public String adslot_name;
    public String adslot_status;
    public String adslot_type;
    public boolean isHasAd;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.adslot_name);
        dest.writeString(this.adslot_status);
        dest.writeString(this.adslot_type);
        dest.writeString(this.adslot_id);
        dest.writeByte(this.isHasAd ? (byte) 1 : (byte) 0);
    }

    public AdInfo() {
    }

    protected AdInfo(Parcel in) {
        this.adslot_name = in.readString();
        this.adslot_status = in.readString();
        this.adslot_type = in.readString();
        this.adslot_id = in.readString();
        this.isHasAd = in.readByte() != 0;
    }

    public static final Creator<AdInfo> CREATOR = new Creator<AdInfo>() {
        @Override
        public AdInfo createFromParcel(Parcel source) {
            return new AdInfo(source);
        }

        @Override
        public AdInfo[] newArray(int size) {
            return new AdInfo[size];
        }
    };
}

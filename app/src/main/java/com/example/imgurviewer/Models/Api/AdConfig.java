package com.example.imgurviewer.Models.Api;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdConfig implements Parcelable {

    @SerializedName("safeFlags")
    @Expose
    private List<String> safeFlags = null;
    @SerializedName("highRiskFlags")
    @Expose
    private List<Object> highRiskFlags = null;
    @SerializedName("unsafeFlags")
    @Expose
    private List<String> unsafeFlags = null;
    @SerializedName("showsAds")
    @Expose
    private boolean showsAds;

    public List<String> getSafeFlags() {
        return safeFlags;
    }

    public void setSafeFlags(List<String> safeFlags) {
        this.safeFlags = safeFlags;
    }

    public List<Object> getHighRiskFlags() {
        return highRiskFlags;
    }

    public void setHighRiskFlags(List<Object> highRiskFlags) {
        this.highRiskFlags = highRiskFlags;
    }

    public List<String> getUnsafeFlags() {
        return unsafeFlags;
    }

    public void setUnsafeFlags(List<String> unsafeFlags) {
        this.unsafeFlags = unsafeFlags;
    }

    public boolean isShowsAds() {
        return showsAds;
    }

    public void setShowsAds(boolean showsAds) {
        this.showsAds = showsAds;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(this.safeFlags);
        dest.writeList(this.highRiskFlags);
        dest.writeStringList(this.unsafeFlags);
        dest.writeByte(this.showsAds ? (byte) 1 : (byte) 0);
    }

    public AdConfig() {
    }

    protected AdConfig(Parcel in) {
        this.safeFlags = in.createStringArrayList();
        this.highRiskFlags = new ArrayList<Object>();
        in.readList(this.highRiskFlags, Object.class.getClassLoader());
        this.unsafeFlags = in.createStringArrayList();
        this.showsAds = in.readByte() != 0;
    }

    public static final Parcelable.Creator<AdConfig> CREATOR = new Parcelable.Creator<AdConfig>() {
        @Override
        public AdConfig createFromParcel(Parcel source) {
            return new AdConfig(source);
        }

        @Override
        public AdConfig[] newArray(int size) {
            return new AdConfig[size];
        }
    };
}
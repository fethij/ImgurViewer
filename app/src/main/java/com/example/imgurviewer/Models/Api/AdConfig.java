package com.example.imgurviewer.Models.Api;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdConfig {

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

}
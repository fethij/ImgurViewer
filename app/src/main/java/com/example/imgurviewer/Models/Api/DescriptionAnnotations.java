package com.example.imgurviewer.Models.Api;

import android.os.Parcel;
import android.os.Parcelable;

public class DescriptionAnnotations implements Parcelable {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public DescriptionAnnotations() {
    }

    protected DescriptionAnnotations(Parcel in) {
    }

    public static final Parcelable.Creator<DescriptionAnnotations> CREATOR = new Parcelable.Creator<DescriptionAnnotations>() {
        @Override
        public DescriptionAnnotations createFromParcel(Parcel source) {
            return new DescriptionAnnotations(source);
        }

        @Override
        public DescriptionAnnotations[] newArray(int size) {
            return new DescriptionAnnotations[size];
        }
    };
}

package com.example.imgurviewer.Models.Api;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tag implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("followers")
    @Expose
    private int followers;
    @SerializedName("total_items")
    @Expose
    private int totalItems;
    @SerializedName("following")
    @Expose
    private boolean following;
    @SerializedName("background_hash")
    @Expose
    private String backgroundHash;
    @SerializedName("thumbnail_hash")
    @Expose
    private Object thumbnailHash;
    @SerializedName("accent")
    @Expose
    private String accent;
    @SerializedName("background_is_animated")
    @Expose
    private boolean backgroundIsAnimated;
    @SerializedName("thumbnail_is_animated")
    @Expose
    private boolean thumbnailIsAnimated;
    @SerializedName("is_promoted")
    @Expose
    private boolean isPromoted;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("logo_hash")
    @Expose
    private Object logoHash;
    @SerializedName("logo_destination_url")
    @Expose
    private Object logoDestinationUrl;
    @SerializedName("description_annotations")
    @Expose
    private DescriptionAnnotations descriptionAnnotations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public boolean isFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }

    public String getBackgroundHash() {
        return backgroundHash;
    }

    public void setBackgroundHash(String backgroundHash) {
        this.backgroundHash = backgroundHash;
    }

    public Object getThumbnailHash() {
        return thumbnailHash;
    }

    public void setThumbnailHash(Object thumbnailHash) {
        this.thumbnailHash = thumbnailHash;
    }

    public String getAccent() {
        return accent;
    }

    public void setAccent(String accent) {
        this.accent = accent;
    }

    public boolean isBackgroundIsAnimated() {
        return backgroundIsAnimated;
    }

    public void setBackgroundIsAnimated(boolean backgroundIsAnimated) {
        this.backgroundIsAnimated = backgroundIsAnimated;
    }

    public boolean isThumbnailIsAnimated() {
        return thumbnailIsAnimated;
    }

    public void setThumbnailIsAnimated(boolean thumbnailIsAnimated) {
        this.thumbnailIsAnimated = thumbnailIsAnimated;
    }

    public boolean isIsPromoted() {
        return isPromoted;
    }

    public void setIsPromoted(boolean isPromoted) {
        this.isPromoted = isPromoted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getLogoHash() {
        return logoHash;
    }

    public void setLogoHash(Object logoHash) {
        this.logoHash = logoHash;
    }

    public Object getLogoDestinationUrl() {
        return logoDestinationUrl;
    }

    public void setLogoDestinationUrl(Object logoDestinationUrl) {
        this.logoDestinationUrl = logoDestinationUrl;
    }

    public DescriptionAnnotations getDescriptionAnnotations() {
        return descriptionAnnotations;
    }

    public void setDescriptionAnnotations(DescriptionAnnotations descriptionAnnotations) {
        this.descriptionAnnotations = descriptionAnnotations;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.displayName);
        dest.writeInt(this.followers);
        dest.writeInt(this.totalItems);
        dest.writeByte(this.following ? (byte) 1 : (byte) 0);
        dest.writeString(this.backgroundHash);
        dest.writeParcelable((Parcelable) this.thumbnailHash, flags);
        dest.writeString(this.accent);
        dest.writeByte(this.backgroundIsAnimated ? (byte) 1 : (byte) 0);
        dest.writeByte(this.thumbnailIsAnimated ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isPromoted ? (byte) 1 : (byte) 0);
        dest.writeString(this.description);
        dest.writeParcelable((Parcelable) this.logoHash, flags);
        dest.writeParcelable((Parcelable) this.logoDestinationUrl, flags);
        dest.writeParcelable(this.descriptionAnnotations, flags);
    }

    public Tag() {
    }

    protected Tag(Parcel in) {
        this.name = in.readString();
        this.displayName = in.readString();
        this.followers = in.readInt();
        this.totalItems = in.readInt();
        this.following = in.readByte() != 0;
        this.backgroundHash = in.readString();
        this.thumbnailHash = in.readParcelable(Object.class.getClassLoader());
        this.accent = in.readString();
        this.backgroundIsAnimated = in.readByte() != 0;
        this.thumbnailIsAnimated = in.readByte() != 0;
        this.isPromoted = in.readByte() != 0;
        this.description = in.readString();
        this.logoHash = in.readParcelable(Object.class.getClassLoader());
        this.logoDestinationUrl = in.readParcelable(Object.class.getClassLoader());
        this.descriptionAnnotations = in.readParcelable(DescriptionAnnotations.class.getClassLoader());
    }

    public static final Parcelable.Creator<Tag> CREATOR = new Parcelable.Creator<Tag>() {
        @Override
        public Tag createFromParcel(Parcel source) {
            return new Tag(source);
        }

        @Override
        public Tag[] newArray(int size) {
            return new Tag[size];
        }
    };
}
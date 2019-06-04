package com.example.imgurviewer.Models.Api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tag {

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

}
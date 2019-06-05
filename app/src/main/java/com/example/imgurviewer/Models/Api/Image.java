package com.example.imgurviewer.Models.Api;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("datetime")
    @Expose
    private int datetime;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("animated")
    @Expose
    private boolean animated;
    @SerializedName("width")
    @Expose
    private int width;
    @SerializedName("height")
    @Expose
    private int height;
    @SerializedName("size")
    @Expose
    private int size;
    @SerializedName("views")
    @Expose
    private int views;
    @SerializedName("bandwidth")
    @Expose
    private int bandwidth;
    @SerializedName("vote")
    @Expose
    private Object vote;
    @SerializedName("favorite")
    @Expose
    private boolean favorite;
    @SerializedName("nsfw")
    @Expose
    private boolean nsfw;
    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("account_url")
    @Expose
    private String accountUrl;
    @SerializedName("account_id")
    @Expose
    private int accountId;
    @SerializedName("is_ad")
    @Expose
    private boolean isAd;
    @SerializedName("in_most_viral")
    @Expose
    private boolean inMostViral;
    @SerializedName("has_sound")
    @Expose
    private boolean hasSound;
    @SerializedName("tags")
    @Expose
    private List<Tag> tags = null;
    @SerializedName("ad_type")
    @Expose
    private int adType;
    @SerializedName("ad_url")
    @Expose
    private String adUrl;
    @SerializedName("edited")
    @Expose
    private int edited;
    @SerializedName("in_gallery")
    @Expose
    private boolean inGallery;
    @SerializedName("topic")
    @Expose
    private String topic;
    @SerializedName("topic_id")
    @Expose
    private int topicId;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("ad_config")
    @Expose
    private AdConfig adConfig;
    @SerializedName("comment_count")
    @Expose
    private int commentCount;
    @SerializedName("favorite_count")
    @Expose
    private int favoriteCount;
    @SerializedName("ups")
    @Expose
    private int ups;
    @SerializedName("downs")
    @Expose
    private int downs;
    @SerializedName("points")
    @Expose
    private int points;
    @SerializedName("score")
    @Expose
    private int score;
    @SerializedName("is_album")
    @Expose
    private boolean isAlbum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDatetime() {
        return datetime;
    }

    public void setDatetime(int datetime) {
        this.datetime = datetime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAnimated() {
        return animated;
    }

    public void setAnimated(boolean animated) {
        this.animated = animated;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(int bandwidth) {
        this.bandwidth = bandwidth;
    }

    public Object getVote() {
        return vote;
    }

    public void setVote(Object vote) {
        this.vote = vote;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean isNsfw() {
        return nsfw;
    }

    public void setNsfw(boolean nsfw) {
        this.nsfw = nsfw;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getAccountUrl() {
        return accountUrl;
    }

    public void setAccountUrl(String accountUrl) {
        this.accountUrl = accountUrl;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public boolean isIsAd() {
        return isAd;
    }

    public void setIsAd(boolean isAd) {
        this.isAd = isAd;
    }

    public boolean isInMostViral() {
        return inMostViral;
    }

    public void setInMostViral(boolean inMostViral) {
        this.inMostViral = inMostViral;
    }

    public boolean isHasSound() {
        return hasSound;
    }

    public void setHasSound(boolean hasSound) {
        this.hasSound = hasSound;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public int getAdType() {
        return adType;
    }

    public void setAdType(int adType) {
        this.adType = adType;
    }

    public String getAdUrl() {
        return adUrl;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }

    public int getEdited() {
        return edited;
    }

    public void setEdited(int edited) {
        this.edited = edited;
    }

    public boolean isInGallery() {
        return inGallery;
    }

    public void setInGallery(boolean inGallery) {
        this.inGallery = inGallery;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public AdConfig getAdConfig() {
        return adConfig;
    }

    public void setAdConfig(AdConfig adConfig) {
        this.adConfig = adConfig;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public int getUps() {
        return ups;
    }

    public void setUps(int ups) {
        this.ups = ups;
    }

    public int getDowns() {
        return downs;
    }

    public void setDowns(int downs) {
        this.downs = downs;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isIsAlbum() {
        return isAlbum;
    }

    public void setIsAlbum(boolean isAlbum) {
        this.isAlbum = isAlbum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeInt(this.datetime);
        dest.writeString(this.type);
        dest.writeByte(this.animated ? (byte) 1 : (byte) 0);
        dest.writeInt(this.width);
        dest.writeInt(this.height);
        dest.writeInt(this.size);
        dest.writeInt(this.views);
        dest.writeInt(this.bandwidth);
        dest.writeParcelable((Parcelable) this.vote, flags);
        dest.writeByte(this.favorite ? (byte) 1 : (byte) 0);
        dest.writeByte(this.nsfw ? (byte) 1 : (byte) 0);
        dest.writeString(this.section);
        dest.writeString(this.accountUrl);
        dest.writeInt(this.accountId);
        dest.writeByte(this.isAd ? (byte) 1 : (byte) 0);
        dest.writeByte(this.inMostViral ? (byte) 1 : (byte) 0);
        dest.writeByte(this.hasSound ? (byte) 1 : (byte) 0);
        dest.writeList(this.tags);
        dest.writeInt(this.adType);
        dest.writeString(this.adUrl);
        dest.writeInt(this.edited);
        dest.writeByte(this.inGallery ? (byte) 1 : (byte) 0);
        dest.writeString(this.topic);
        dest.writeInt(this.topicId);
        dest.writeString(this.link);
        dest.writeParcelable(this.adConfig, flags);
        dest.writeInt(this.commentCount);
        dest.writeInt(this.favoriteCount);
        dest.writeInt(this.ups);
        dest.writeInt(this.downs);
        dest.writeInt(this.points);
        dest.writeInt(this.score);
        dest.writeByte(this.isAlbum ? (byte) 1 : (byte) 0);
    }

    public Image() {
    }

    protected Image(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.datetime = in.readInt();
        this.type = in.readString();
        this.animated = in.readByte() != 0;
        this.width = in.readInt();
        this.height = in.readInt();
        this.size = in.readInt();
        this.views = in.readInt();
        this.bandwidth = in.readInt();
        this.vote = in.readParcelable(Object.class.getClassLoader());
        this.favorite = in.readByte() != 0;
        this.nsfw = in.readByte() != 0;
        this.section = in.readString();
        this.accountUrl = in.readString();
        this.accountId = in.readInt();
        this.isAd = in.readByte() != 0;
        this.inMostViral = in.readByte() != 0;
        this.hasSound = in.readByte() != 0;
        this.tags = new ArrayList<Tag>();
        in.readList(this.tags, Tag.class.getClassLoader());
        this.adType = in.readInt();
        this.adUrl = in.readString();
        this.edited = in.readInt();
        this.inGallery = in.readByte() != 0;
        this.topic = in.readString();
        this.topicId = in.readInt();
        this.link = in.readString();
        this.adConfig = in.readParcelable(AdConfig.class.getClassLoader());
        this.commentCount = in.readInt();
        this.favoriteCount = in.readInt();
        this.ups = in.readInt();
        this.downs = in.readInt();
        this.points = in.readInt();
        this.score = in.readInt();
        this.isAlbum = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel source) {
            return new Image(source);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
}
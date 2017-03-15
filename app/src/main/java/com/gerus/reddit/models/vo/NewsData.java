package com.gerus.reddit.models.vo;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by gerus-mac on 14/03/17.
 */

@DatabaseTable
public class NewsData implements Parcelable {

    public static final String ID = NewsData.class.getSimpleName();

    @DatabaseField
    private String subreddit;

    @DatabaseField
    private boolean contest_mode;

    @DatabaseField(id=true, useGetSet=true)
    private String id;

    @DatabaseField
    private String author;

    @DatabaseField
    private boolean over_18;

    @DatabaseField
    private String thumbnail;

    @DatabaseField
    private String permalink;

    @DatabaseField
    private int created;

    @DatabaseField
    private String url;

    @DatabaseField
    private String title;

    @DatabaseField
    private int num_comments;

    public String getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

    public boolean isContest_mode() {
        return contest_mode;
    }

    public void setContest_mode(boolean contest_mode) {
        this.contest_mode = contest_mode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isOver_18() {
        return over_18;
    }

    public void setOver_18(boolean over_18) {
        this.over_18 = over_18;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNum_comments() {
        return num_comments;
    }

    public void setNum_comments(int num_comments) {
        this.num_comments = num_comments;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.subreddit);
        dest.writeByte(this.contest_mode ? (byte) 1 : (byte) 0);
        dest.writeString(this.id);
        dest.writeString(this.author);
        dest.writeByte(this.over_18 ? (byte) 1 : (byte) 0);
        dest.writeString(this.thumbnail);
        dest.writeString(this.permalink);
        dest.writeInt(this.created);
        dest.writeString(this.url);
        dest.writeString(this.title);
        dest.writeInt(this.num_comments);
    }

    public NewsData() {
    }

    protected NewsData(Parcel in) {
        this.subreddit = in.readString();
        this.contest_mode = in.readByte() != 0;
        this.id = in.readString();
        this.author = in.readString();
        this.over_18 = in.readByte() != 0;
        this.thumbnail = in.readString();
        this.permalink = in.readString();
        this.created = in.readInt();
        this.url = in.readString();
        this.title = in.readString();
        this.num_comments = in.readInt();
    }

    public static final Parcelable.Creator<NewsData> CREATOR = new Parcelable.Creator<NewsData>() {
        @Override
        public NewsData createFromParcel(Parcel source) {
            return new NewsData(source);
        }

        @Override
        public NewsData[] newArray(int size) {
            return new NewsData[size];
        }
    };
}

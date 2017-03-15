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
public class News implements Parcelable {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField
    private String kind;

    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private NewsData data;

    public String getKind() {
        return kind;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public NewsData getData() {
        return data;
    }

    public void setData(NewsData data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kind);
        dest.writeParcelable(this.data, flags);
    }

    public News() {
    }

    protected News(Parcel in) {
        this.kind = in.readString();
        this.data = in.readParcelable(NewsData.class.getClassLoader());
    }

    public static final Parcelable.Creator<News> CREATOR = new Parcelable.Creator<News>() {
        @Override
        public News createFromParcel(Parcel source) {
            return new News(source);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };
}

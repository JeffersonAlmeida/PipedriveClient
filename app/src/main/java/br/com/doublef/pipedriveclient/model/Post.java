package br.com.doublef.pipedriveclient.model;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Data;

@Data
public class Post implements Parcelable {

    private long userId;
    private long id;
    private String title;
    private String body;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.userId);
        dest.writeLong(this.id);
        dest.writeString(this.title);
        dest.writeString(this.body);
    }

    protected Post(Parcel in) {
        this.userId = in.readLong();
        this.id = in.readLong();
        this.title = in.readString();
        this.body = in.readString();
    }

    public static final Parcelable.Creator<Post> CREATOR = new Parcelable.Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel source) {
            return new Post(source);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };
}
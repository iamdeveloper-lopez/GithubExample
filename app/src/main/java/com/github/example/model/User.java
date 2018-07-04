package com.github.example.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {

    public long id;
    @SerializedName("login")
    public String username;
    public String name;
    @SerializedName("location")
    public String country;
    @SerializedName("avatar_url")
    public String image_url;

    protected User(Parcel in) {
        id = in.readLong();
        username = in.readString();
        name = in.readString();
        country = in.readString();
        image_url = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeLong(id);
        dest.writeString(username);
        dest.writeString(name);
        dest.writeString(country);
        dest.writeString(image_url);
    }
}

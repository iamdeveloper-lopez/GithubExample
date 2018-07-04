package com.github.example.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

public class Repo implements Parcelable {

    public long id;
    public String name;
    public String description;

    protected Repo(Parcel in) {
        id = in.readLong();
        name = in.readString();
        description = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Repo> CREATOR = new Creator<Repo>() {
        @Override
        public Repo createFromParcel(Parcel in) {
            return new Repo(in);
        }

        @Override
        public Repo[] newArray(int size) {
            return new Repo[size];
        }
    };

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

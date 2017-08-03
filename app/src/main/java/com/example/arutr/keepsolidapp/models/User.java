package com.example.arutr.keepsolidapp.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Artur Romasiuk
 */

public class User implements Parcelable{

    protected User(Parcel in) {
        username = in.readString();
        userId = in.readInt();
        isOnline = in.readByte() != 0;
        userAddress = in.readString();
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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeInt(userId);
        dest.writeByte((byte) (isOnline ? 1 : 0));
        dest.writeString(userAddress);
    }

    public enum Category {
        FRIENDS("Друзья"),
        FAMILY("Семья"),
        WORK("Работа"),
        OTHER("Другое");

        private String description;

        Category(String description) {
            this.description = description;
        }

        public String getDescription() {return description;}
    };

    private String username;
    private int userId;
    private boolean isOnline;
    private String userAddress;
    private Category category;

    public User(String username, int userId, boolean isOnline, String userAddress, Category category) {
        this.username = username;
        this.userId = userId;
        this.isOnline = isOnline;
        this.userAddress = userAddress;
        this.category = category;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return userId == user.userId;

    }

    @Override
    public int hashCode() {
        return userId;
    }
}

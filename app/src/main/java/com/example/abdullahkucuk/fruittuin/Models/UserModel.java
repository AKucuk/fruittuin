package com.example.abdullahkucuk.fruittuin.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by abdullah.kucuk on 16-10-2016.
 */
public class UserModel implements Parcelable {
    public String name;
    public int leeftijd;
    public String locatie;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(leeftijd);
        dest.writeString(locatie);
    }

    public static final Parcelable.Creator<UserModel> CREATOR = new Parcelable.Creator<UserModel>() {
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }

        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };
    private UserModel(Parcel in) {
        name = in.readString();
        leeftijd = in.readInt();
        locatie = in.readString();
    }
    public UserModel() {

    }
}

package com.uc.uts_protech.model;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.uc.uts_protech.R;

public class User implements Parcelable{
    private String nama;
    private String umur;
    private String alamat;

    public User(String nama, String umur, String alamat) {
        this.nama = nama;
        this.umur = umur;
        this.alamat = alamat;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getUmur() {
        return umur;
    }

    public String getAlamat() {
        return alamat;
    }

    public static Creator<User> getCREATOR() {
        return CREATOR;
    }

    protected User(Parcel in) {
        nama = in.readString();
        umur = in.readString();
        alamat = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nama);
        dest.writeString(umur);
        dest.writeString(alamat);
    }

    @Override
    public int describeContents() {
        return 0;
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
}

package com.dongumen.nickolas.ownweather2.models.enteties.weatherList;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Temp implements Parcelable{

    @SerializedName("min")
    private double min;

    @SerializedName("max")
    private double max;

    @SerializedName("eve")
    private double eve;

    @SerializedName("night")
    private double night;

    @SerializedName("day")
    private double day;

    @SerializedName("morn")
    private double morn;

    protected Temp(Parcel in) {
        min = in.readDouble();
        max = in.readDouble();
        eve = in.readDouble();
        night = in.readDouble();
        day = in.readDouble();
        morn = in.readDouble();
    }

    public static final Creator<Temp> CREATOR = new Creator<Temp>() {
        @Override
        public Temp createFromParcel(Parcel in) {
            return new Temp(in);
        }

        @Override
        public Temp[] newArray(int size) {
            return new Temp[size];
        }
    };

    public void setMin(double min) {
        this.min = min;
    }

    public double getMin() {
        return min;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMax() {
        return max;
    }

    public void setEve(double eve) {
        this.eve = eve;
    }

    public double getEve() {
        return eve;
    }

    public void setNight(double night) {
        this.night = night;
    }

    public double getNight() {
        return night;
    }

    public void setDay(double day) {
        this.day = day;
    }

    public double getDay() {
        return day;
    }

    public void setMorn(double morn) {
        this.morn = morn;
    }

    public double getMorn() {
        return morn;
    }

    @Override
    public String toString() {
        return
                "Temp{" +
                        "min = '" + min + '\'' +
                        ",max = '" + max + '\'' +
                        ",eve = '" + eve + '\'' +
                        ",night = '" + night + '\'' +
                        ",day = '" + day + '\'' +
                        ",morn = '" + morn + '\'' +
                        "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(eve);
        parcel.writeDouble(max);
        parcel.writeDouble(min);
        parcel.writeDouble(morn);
        parcel.writeDouble(day);
        parcel.writeDouble(night);
    }
}
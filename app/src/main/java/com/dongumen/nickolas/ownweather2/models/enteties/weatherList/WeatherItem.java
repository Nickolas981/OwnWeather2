package com.dongumen.nickolas.ownweather2.models.enteties.weatherList;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class WeatherItem implements Parcelable  {

    @SerializedName("icon")
    private String icon;

    @SerializedName("description")
    private String description;

    @SerializedName("main")
    private String main;

    @SerializedName("id")
    private int id;

    protected WeatherItem(Parcel in) {
        icon = in.readString();
        description = in.readString();
        main = in.readString();
        id = in.readInt();
    }

    public static final Creator<WeatherItem> CREATOR = new Creator<WeatherItem>() {
        @Override
        public WeatherItem createFromParcel(Parcel in) {
            return new WeatherItem(in);
        }

        @Override
        public WeatherItem[] newArray(int size) {
            return new WeatherItem[size];
        }
    };

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getMain() {
        return main;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return
                "WeatherItem{" +
                        "icon = '" + icon + '\'' +
                        ",description = '" + description + '\'' +
                        ",main = '" + main + '\'' +
                        ",id = '" + id + '\'' +
                        "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(icon);
        parcel.writeString(description);
        parcel.writeString(main);
        parcel.writeInt(id);
    }
}
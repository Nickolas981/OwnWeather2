package com.dongumen.nickolas.ownweather2.models.enteties.weatherList;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListItem implements Parcelable{

    @SerializedName("dt")
    private long dt;

    @SerializedName("temp")
    private Temp temp;

    @SerializedName("deg")
    private int deg;

    @SerializedName("weather")
    private List<WeatherItem> weather;

    @SerializedName("humidity")
    private int humidity;

    @SerializedName("pressure")
    private double pressure;

    @SerializedName("clouds")
    private int clouds;

    @SerializedName("speed")
    private double speed;


    protected ListItem(Parcel in) {
        dt = in.readLong();
        temp = in.readParcelable(Temp.class.getClassLoader());
        deg = in.readInt();
        weather = in.createTypedArrayList(WeatherItem.CREATOR);
        humidity = in.readInt();
        pressure = in.readDouble();
        clouds = in.readInt();
        speed = in.readDouble();
    }

    public static final Creator<ListItem> CREATOR = new Creator<ListItem>() {
        @Override
        public ListItem createFromParcel(Parcel in) {
            return new ListItem(in);
        }

        @Override
        public ListItem[] newArray(int size) {
            return new ListItem[size];
        }
    };

    public void setDt(int dt) {
        this.dt = dt;
    }

    public long  getDt() {
        return dt;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    public Temp getTemp() {
        return temp;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public int getDeg() {
        return deg;
    }

    public void setWeather(List<WeatherItem> weather) {
        this.weather = weather;
    }

    public List<WeatherItem> getWeather() {
        return weather;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getPressure() {
        return pressure;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public int getClouds() {
        return clouds;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return
                "ListItem{" +
                        "dt = '" + dt + '\'' +
                        ",temp = '" + temp + '\'' +
                        ",deg = '" + deg + '\'' +
                        ",weather = '" + weather + '\'' +
                        ",humidity = '" + humidity + '\'' +
                        ",pressure = '" + pressure + '\'' +
                        ",clouds = '" + clouds + '\'' +
                        ",speed = '" + speed + '\'' +
                        "}";
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(dt);
        parcel.writeParcelable(temp, i);
        parcel.writeInt(deg);
        parcel.writeTypedList(weather);
        parcel.writeInt(humidity);
        parcel.writeDouble(pressure);
        parcel.writeInt(clouds);
        parcel.writeDouble(speed);
    }
}
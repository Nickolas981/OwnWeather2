package com.dongumen.nickolas.ownweather2.models;

import com.google.android.gms.maps.model.LatLng;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Nickolas on 17.01.2018.
 */

public class City extends RealmObject {

    private String name;
    private double lat, lng;
    private String id;

    public void setName(String name) {
        this.name = name;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public City(String name, double lat, double lng, String id) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.id = id;
    }

    public City() {
    }

    public void setLatLng(LatLng latLng) {
        setLat(latLng.latitude);
        setLng(latLng.longitude);
    }
}

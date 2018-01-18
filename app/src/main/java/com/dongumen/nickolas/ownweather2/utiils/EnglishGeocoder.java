package com.dongumen.nickolas.ownweather2.utiils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Nickolas on 17.01.2018.
 */

public class EnglishGeocoder {
    public static String getCityNameByCoordinates(double lat, double lon, Context context) throws IOException {
        Geocoder mGeocoder = new Geocoder(context, new Locale("en"));
        List<Address> addresses = mGeocoder.getFromLocation(lat, lon, 1);
        if (addresses != null && addresses.size() > 0) {
            return addresses.get(0).getLocality();
        }
        return null;
    }
}

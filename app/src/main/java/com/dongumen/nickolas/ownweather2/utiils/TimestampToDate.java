package com.dongumen.nickolas.ownweather2.utiils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Nickolas on 14.01.2018.
 */

public class TimestampToDate {

    long timestamp;

    public TimestampToDate(long timestamp) {
        this.timestamp = timestamp;
    }


    public String getDay(){
        Date date = new Date(timestamp*1000);
        SimpleDateFormat format =  new SimpleDateFormat("EEEE");
        return format.format(date);
    }
    public String getDate(){
        Date date = new Date(timestamp*1000);
        SimpleDateFormat format =  new SimpleDateFormat("MMMM dd");
        return format.format(date);
    }
}

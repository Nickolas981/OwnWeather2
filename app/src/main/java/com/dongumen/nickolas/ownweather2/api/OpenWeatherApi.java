package com.dongumen.nickolas.ownweather2.api;

import com.dongumen.nickolas.ownweather2.models.enteties.currentWeather.CurrentWeather;
import com.dongumen.nickolas.ownweather2.models.enteties.weatherList.WeatherResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Nickolas on 13.01.2018.
 */

public interface OpenWeatherApi {
/*
    @GET("data/2.5/forecast/daily")
    Observable<WeatherResponse> getWeatherList(@Query("appid") String appid,
                                               @Query("units") String units,
                                               @Query("cnt") String cnt,
                                               @Query("lat") double lat,
                                               @Query("lon") double lng);
*/

     @GET("data/2.5/forecast/daily")
    Observable<WeatherResponse> getWeatherList(@Query("appid") String appid,
                                               @Query("units") String units,
                                               @Query("cnt") String cnt,
                                               @Query("q") String city);

    @GET("data/2.5/weather")
    Observable<CurrentWeather> getCurrentWeather(@Query("appid") String appid,
                                                 @Query("units") String units,
                                                 @Query("q") String city);
}

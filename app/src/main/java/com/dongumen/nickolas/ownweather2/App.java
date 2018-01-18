package com.dongumen.nickolas.ownweather2;

import android.app.Application;

import com.dongumen.nickolas.ownweather2.api.OpenWeatherApi;

import io.realm.Realm;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nickolas on 15.01.2018.
 */
public class App extends Application {

    private static OpenWeatherApi openWeatherApi;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL) //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create())//Конвертер, необходимый для преобразования JSON'а в объекты
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        openWeatherApi = retrofit.create(OpenWeatherApi.class); //Создаем объект, при помощи которого будем выполнять запросы
    }

    public static OpenWeatherApi getApi() {
        return openWeatherApi;
    }
}
package com.dongumen.nickolas.ownweather2.presenters;

import com.dongumen.nickolas.ownweather2.App;
import com.dongumen.nickolas.ownweather2.Constants;
import com.dongumen.nickolas.ownweather2.api.OpenWeatherApi;
import com.dongumen.nickolas.ownweather2.utiils.rx.RxRetryWithDelay;
import com.dongumen.nickolas.ownweather2.views.WeatherListView;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Nickolas on 13.01.2018.
 */

public class WeatherListPresenter extends MvpBasePresenter<WeatherListView> {
    private OpenWeatherApi api;

    public WeatherListPresenter() {
        api = App.getApi();
    }

    public void loadWeatherList(final boolean pullToRefresh, String name) {

        api.getWeatherList(Constants.APPID, Constants.UNITS, Constants.CNT, name)
                .retryWhen(new RxRetryWithDelay())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weatherResponse -> {
                    getView().setData(weatherResponse);
                    getView().showContent();
                }, (e) -> {
                    if (isViewAttached())
                        getView().showError(e, pullToRefresh );
                });
    }

}

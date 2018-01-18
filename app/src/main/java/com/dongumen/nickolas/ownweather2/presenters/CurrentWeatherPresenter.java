package com.dongumen.nickolas.ownweather2.presenters;

import com.dongumen.nickolas.ownweather2.App;
import com.dongumen.nickolas.ownweather2.Constants;
import com.dongumen.nickolas.ownweather2.api.OpenWeatherApi;
import com.dongumen.nickolas.ownweather2.utiils.rx.RxRetryWithDelay;
import com.dongumen.nickolas.ownweather2.views.CurrentWeatherView;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Nickolas on 17.01.2018.
 */

public class CurrentWeatherPresenter extends MvpBasePresenter<CurrentWeatherView> {

    private OpenWeatherApi api;

    public CurrentWeatherPresenter() {
        api = App.getApi();
    }

    public void loadCurrentWeather(final boolean pullToRefresh, String name) {
        api.getCurrentWeather(Constants.APPID, Constants.UNITS, name)
                .retryWhen(new RxRetryWithDelay())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(currentWeather -> {
                    getView().setData(currentWeather);
                    getView().showContent();
                }, (e) -> {
                    if (isViewAttached())
                        getView().showError(e, pullToRefresh);
                });
    }

}

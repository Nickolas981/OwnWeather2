package com.dongumen.nickolas.ownweather2.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dongumen.nickolas.ownweather2.R;
import com.dongumen.nickolas.ownweather2.activities.MainActivity;
import com.dongumen.nickolas.ownweather2.models.enteties.currentWeather.CurrentWeather;
import com.dongumen.nickolas.ownweather2.presenters.CurrentWeatherPresenter;
import com.dongumen.nickolas.ownweather2.utiils.ResourceId;
import com.dongumen.nickolas.ownweather2.utiils.TimestampToDate;
import com.dongumen.nickolas.ownweather2.views.CurrentWeatherView;
import com.google.android.gms.maps.model.LatLng;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateFragment;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrentWeatherFragment extends MvpLceViewStateFragment<SwipeRefreshLayout, CurrentWeather,
        CurrentWeatherView, CurrentWeatherPresenter> implements CurrentWeatherView, IBaseWeather, SwipeRefreshLayout.OnRefreshListener {


//    private LatLng latLng;
    private CurrentWeather currentWeather;
//    private String mCityName;
//    private String googlePlaceID;

    @BindView(R.id.weatherDate)
    TextView weatherDate;
    @BindView(R.id.weatherStateImage)
    ImageView weatherStateImage;
    @BindView(R.id.weatherState)
    TextView weatherState;
    @BindView(R.id.weatherDay)
    TextView weatherDay;
    @BindView(R.id.temperature_header)
    TextView temperatureHeader;
    @BindView(R.id.max_temperature_header)
    TextView maxTemparatureHeader;
    @BindView(R.id.min_temperature_header)
    TextView minTemparatureHeader;

    public CurrentWeatherFragment() {
    }


    public void notifyFragment() {
        loadData(true);
    }

    @Override
    public CurrentWeatherPresenter createPresenter() {
        return new CurrentWeatherPresenter();
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @Override
    public LceViewState<CurrentWeather, CurrentWeatherView> createViewState() {
        return new RetainingLceViewState<>();
    }

    @Override
    public CurrentWeather getData() {
        return currentWeather;
    }


    public static CurrentWeatherFragment newInstance() {
        CurrentWeatherFragment fragment = new CurrentWeatherFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_current_weather, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        contentView.setOnRefreshListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void setData(CurrentWeather data) {
        currentWeather = data;
        refreshData();
    }

    @SuppressLint("SetTextI18n")
    private void refreshData() {
        String resourceId = "d" + currentWeather.getWeather().get(0).getIcon().substring(0,2);
        weatherStateImage.setImageResource(new ResourceId(resourceId, R.drawable.class).get());
        weatherState.setText(getString(new ResourceId(resourceId, R.string.class).get()));
        weatherDate.setText(new TimestampToDate(currentWeather.getDt()).getDate());
        temperatureHeader.setText(
                Integer.toString((int) currentWeather.getMain().getTemp()) + "°");
        maxTemparatureHeader.setText(
                Integer.toString((int) currentWeather.getMain().getTempMax()) + "°");
        minTemparatureHeader.setText(
                Integer.toString((int) currentWeather.getMain().getTempMin()) + "°");

    }

    @Override
    public void loadData(boolean pullToRefresh) {
        if (getMainActivity().getCity().getName() != null) {
            presenter.loadCurrentWeather(pullToRefresh,(getMainActivity().getCity().getName()));
        }
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override
    public void showContent() {
        super.showContent();
        contentView.setRefreshing(false);
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        contentView.setRefreshing(false);
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        super.showLoading(pullToRefresh);
        contentView.setRefreshing(pullToRefresh);
    }

    private MainActivity getMainActivity(){
        return ((MainActivity)getActivity());
    }
}

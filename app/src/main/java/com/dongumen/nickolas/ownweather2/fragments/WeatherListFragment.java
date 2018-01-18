package com.dongumen.nickolas.ownweather2.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dongumen.nickolas.ownweather2.R;
import com.dongumen.nickolas.ownweather2.activities.MainActivity;
import com.dongumen.nickolas.ownweather2.models.enteties.weatherList.WeatherResponse;
import com.dongumen.nickolas.ownweather2.presenters.WeatherListPresenter;
import com.dongumen.nickolas.ownweather2.views.WeatherListView;
import com.dongumen.nickolas.ownweather2.widgets.adapters.WeatherListAdapter;
import com.google.android.gms.maps.model.LatLng;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateFragment;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WeatherListFragment extends MvpLceViewStateFragment<SwipeRefreshLayout, WeatherResponse,
        WeatherListView, WeatherListPresenter> implements WeatherListView, IBaseWeather{


    private OnFragmentInteractionListener mListener;

    @BindView(R.id.list_view)
    RecyclerView recyclerView;


    private WeatherListAdapter adapter;


   /* private String mCityName = "";
    private LatLng latLng;
    private String googlePlaceID;
*/


    public WeatherListFragment() {
    }

    @Override
    public LceViewState<WeatherResponse, WeatherListView> createViewState() {
        return new RetainingLceViewState<>();
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @Override
    public WeatherListPresenter createPresenter() {
        WeatherListPresenter listPresenter = new WeatherListPresenter();
        return listPresenter;
    }


    public static WeatherListFragment newInstance() {
        WeatherListFragment fragment = new WeatherListFragment();
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
        return inflater.inflate(R.layout.fragment_weather_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        contentView.setOnRefreshListener(() -> loadData(true));
        adapter = new WeatherListAdapter(getActivity());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void notifyFragment() {
        loadData(true);
    }

    @Override
    public void setData(WeatherResponse data) {
        adapter.setWeatherResponse(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        if (getMainActivity().getCity().getName() != null) {
            presenter.loadWeatherList(pullToRefresh,getMainActivity().getCity().getName());
        }else {
            mListener.callAutoComplete();
        }
    }


    public interface OnFragmentInteractionListener {
        void callAutoComplete();
    }

    @Override public void showContent() {
        super.showContent();
        contentView.setRefreshing(false);
    }

    @Override public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        contentView.setRefreshing(false);
    }

    @Override public void showLoading(boolean pullToRefresh) {
        super.showLoading(pullToRefresh);
        contentView.setRefreshing(pullToRefresh);
    }

    @Override
    public WeatherResponse getData() {
        return adapter == null ? null : adapter.getWeatherResponse();
    }

    private MainActivity getMainActivity(){
        return ((MainActivity)getActivity());
    }


}

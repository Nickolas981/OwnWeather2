package com.dongumen.nickolas.ownweather2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.dongumen.nickolas.ownweather2.R;
import com.dongumen.nickolas.ownweather2.fragments.CurrentWeatherFragment;
import com.dongumen.nickolas.ownweather2.fragments.IBaseWeather;
import com.dongumen.nickolas.ownweather2.fragments.WeatherListFragment;
import com.dongumen.nickolas.ownweather2.models.City;
import com.dongumen.nickolas.ownweather2.utiils.EnglishGeocoder;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity
        implements WeatherListFragment.OnFragmentInteractionListener {

    private static final int NUM_PAGES = 2;
    private static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;

    @BindView(R.id.view_pagger)
    ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private Realm realm;


    private List<Fragment> list;

    private City city;

    public City getCity() {
        return city;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        loadCity();
        city.addChangeListener(realmModel -> notifyAllFragments());
        list = new ArrayList<>();
        list.add(WeatherListFragment.newInstance());
        list.add(CurrentWeatherFragment.newInstance());
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                try {
                    Intent intent =
                            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                                    .build(this);
                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException |
                        GooglePlayServicesNotAvailableException ignored) {
                }
        }
        return false;
    }

    private void saveCity() {
        realm.executeTransaction(
                realm -> {
                    realm.where(City.class).findAll().deleteAllFromRealm();
                    realm.copyToRealm(city);
                });
    }

    private void saveCity(String id, String name, double lat, double lon) {
        realm.executeTransaction(
                realm -> {
                    city.setLat(lat);
                    city.setLng(lon);
                    city.setName(name);
                    city.setId(id);
                });
    }

    private void loadCity() {
        realm.executeTransaction(realm -> city = realm.where(City.class).findFirst());
    }


    @Override
    public void callAutoComplete() {
        callAutoCompleteActivity();
    }

    private void callAutoCompleteActivity() {
        try {
            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .build(this);
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException |
                GooglePlayServicesNotAvailableException ignored) {
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                String name = "";
                String id = place.getId();
                double lat = place.getLatLng().latitude;
                double lng = place.getLatLng().longitude;
//                city.setName(place.getName().toString());
//                city.setLatLng(place.getLatLng());
//                city.setId(place.getId());
                try {
                    name = EnglishGeocoder.getCityNameByCoordinates(
                            lat, lng, this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                saveCity(id, name, lat, lng);
//                notifyAllFragments();
            } else {
                Status status = PlaceAutocomplete.getStatus(this, data);
                Toast.makeText(MainActivity.this,
                        status.getStatusMessage(),
                        Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void notifyAllFragments() {
        for (Fragment fragment : list) {
            IBaseWeather baseWeather = (IBaseWeather) fragment;
            baseWeather.notifyFragment();
        }
    }


    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}

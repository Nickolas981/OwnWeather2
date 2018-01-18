package com.dongumen.nickolas.ownweather2.widgets.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dongumen.nickolas.ownweather2.R;
import com.dongumen.nickolas.ownweather2.activities.WeatherActivity;
import com.dongumen.nickolas.ownweather2.models.enteties.weatherList.WeatherResponse;
import com.dongumen.nickolas.ownweather2.utiils.ResourceId;
import com.dongumen.nickolas.ownweather2.utiils.TimestampToDate;
import com.dongumen.nickolas.ownweather2.utiils.WordUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nickolas on 13.01.2018.
 */

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.WeatherHolder> {

    private WeatherResponse weatherResponse;
    private Context context;

    public WeatherResponse getWeatherResponse() {
        return weatherResponse;
    }

    public WeatherListAdapter(Context context) {
        this.context = context;
        weatherResponse = new WeatherResponse();
    }

    public void setWeatherResponse(WeatherResponse weatherResponse) {
        this.weatherResponse = weatherResponse;
    }

    @Override
    public WeatherHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_weather_view, parent, false);
        WeatherHolder wh = new WeatherHolder(v);
        return wh;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(WeatherHolder wh, int position) {
        wh.day.setText(WordUtils.capitalize(new TimestampToDate(weatherResponse.getList()
                .get(position).getDt()).getDay()));
        wh.maxTemp.setText(Integer.toString(
                ((int) weatherResponse.getList().get(position).getTemp().getMax())) + "°");
        wh.minTemp.setText(Integer.toString(
                ((int) weatherResponse.getList().get(position).getTemp().getMin())) + "°");

        String resourceId = "d" + weatherResponse.getList()
                .get(position).getWeather().get(0).getIcon().substring(0, 2);

        wh.weatherImage.setImageResource(new ResourceId(resourceId, R.drawable.class).get());
        wh.state.setText(context.getString(new ResourceId(resourceId, R.string.class).get()));

        wh.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, WeatherActivity.class);
            intent.putExtra("weather", weatherResponse.getList().get(position));
            context.startActivity(intent);
        });
//        wh.minTemp.setText((int) weatherResponse.getList().get(position).getTemp().getMin());
    }



    @Override
    public int getItemCount() {
        return weatherResponse.getList().size();
    }

    class WeatherHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.weatherImage)
        ImageView weatherImage;
        @BindView(R.id.weatherDay)
        TextView day;
        @BindView(R.id.weatherMaxTemp)
        TextView maxTemp;
        @BindView(R.id.weatherMinTemp)
        TextView minTemp;
        @BindView(R.id.weatherState)
        TextView state;

        WeatherHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

package com.example.weatherforecast.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.data.models.network.Daily
import com.example.weatherforecast.databinding.ItemWeeklyForecastBinding
import com.example.weatherforecast.util.getDateTimeFormatted
import com.example.weatherforecast.util.getIconResources
import com.example.weatherforecast.util.getTemp

class FutureWeatherAdapter(val context: Context): RecyclerView.Adapter<FutureWeatherAdapter.ViewHolder>() {

    private var list: List<Daily>?=null

    fun setData(list: List<Daily>){
        this.list=list
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemWeeklyForecastBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return  ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list?.get(position),context)
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    class ViewHolder(private val binding: ItemWeeklyForecastBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(weather: Daily?, context: Context) {
            if (weather != null) {
//                binding.date.text= getDateTimeFormatted(weather.dt.toString())
                binding.highTemp.text= getTemp(weather.temp.max.toInt())
                binding.lowTemp.text= getTemp(weather.temp.min.toInt())
                binding.icon.getIconResources(
                    context = context,
                    weather?.weather?.get(0)?.description
                )
            }
        }

    }
}
package com.example.weatherforecast.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.data.models.network.Hourly
import com.example.weatherforecast.data.models.network.Weather
import com.example.weatherforecast.databinding.ItemHourlyForecastBinding
import com.example.weatherforecast.util.getDateTimeFormatted
import com.example.weatherforecast.util.getIconResources
import com.example.weatherforecast.util.getTemp

class HourlyWeatherAdapter(val context: Context): RecyclerView.Adapter<HourlyWeatherAdapter.ViewHolder>()
{

    private var list: List<Hourly>?=null

    fun setData(list: List<Hourly?>?){
        this.list= list as List<Hourly>?
    }


    class ViewHolder(private val binding: ItemHourlyForecastBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(hourly: Hourly?, context: Context) {

            binding.icon.getIconResources(
                context = context,
                hourly?.weather?.get(0)?.description
            )
            if (hourly != null) {
                binding.temp.text= getTemp(hourly.temp.minus(273).toInt())
            }
//            if (hourly != null) {
//                binding.time.text= getDateTimeFormatted(hourly.dt.toString().substring(0,hourly.dt.toString().length-3))
//            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHourlyForecastBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list?.get(position),context)
    }

    override fun getItemCount(): Int {
        return list!!.size
    }
}
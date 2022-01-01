package com.example.weatherforecast.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.data.models.network.Daily
import com.example.weatherforecast.databinding.ItemWeeklyForecastBinding

class FutureWeatherAdapter: RecyclerView.Adapter<FutureWeatherAdapter.ViewHolder>() {

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
        holder.bind(list?.get(position))

    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    class ViewHolder(private val binding: ItemWeeklyForecastBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(weather: Daily?) {
            if (weather != null) {

//                binding.textViewTemperature.text=weather.temp.day.toString()
//                binding.textViewCondition.text=weather.weather[0].main
//                binding.textViewDate.text= getDateTime(weather.dt.toString())

            }
        }

    }
}
package com.example.weatherforecast.ui.futureweather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.data.models.network.Weather
import com.example.weatherforecast.databinding.ItemFutureWeatherBinding

class FutureWeatherAdapter: RecyclerView.Adapter<FutureWeatherAdapter.ViewHolder>() {

    private var list: List<Weather>?=null

    fun setData(list: List<Weather>){
        this.list=list
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFutureWeatherBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return  ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list?.get(position))

    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    class ViewHolder(private val binding: ItemFutureWeatherBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(weather: Weather?) {
            binding.apply {
                textViewTemperature.text=weather?.main
                textViewCondition.text=weather?.description
            }
        }

    }
}
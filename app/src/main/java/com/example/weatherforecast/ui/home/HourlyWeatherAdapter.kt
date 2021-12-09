package com.example.weatherforecast.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast.data.models.network.FutureWeatherResponse.Hourly
import com.example.weatherforecast.databinding.ItemHourBinding
import com.example.weatherforecast.util.getDateTime

class HourlyWeatherAdapter: RecyclerView.Adapter<HourlyWeatherAdapter.ViewHolder>()
{

    private var list: List<Hourly>?=null

    fun setData(list: List<Hourly?>?){
        this.list= list as List<Hourly>?
    }


    class ViewHolder(private val binding: ItemHourBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(hourly: Hourly?) {
            binding.tvTemp.text= hourly?.temp?.toInt().toString()
            binding.tvTime.text= getDateTime(hourly?.dt.toString())
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHourBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list?.get(position))
    }

    override fun getItemCount(): Int {
        return list!!.size
    }
}
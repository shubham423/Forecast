package com.example.weatherforecast.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.weatherforecast.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewmodel: HomeViewModel by activityViewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.getWeatherDataByCityName("delhi")
        initObservers()
    }

    private fun initObservers() {
        viewmodel.weatherResponse.observe(viewLifecycleOwner){
            Log.d("HomeFragment","${it.body()}")
            binding.address.text= it.body()?.name +","+ (it.body()?.sys?.country ?: "")
            binding.temp.text= it.body()?.main?.temp.toString()+"°C"
            binding.status.text= it.body()?.weather?.get(0)?.description ?: ""
            binding.updatedAt.text="Updated at: "+ SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(
                (it.body()?.dt)?.times(1000)?.toLong()?.let { it1 ->
                    Date(
                        it1
                    )
                })

            binding.tempMin.text="Min Temp: " + (it.body()?.main?.tempMin ?: "") +"°C"
            binding.tempMax.text="Max Temp: " + (it.body()?.main?.tempMax ?: "") +"°C"
            binding.pressure.text= it.body()?.main?.pressure.toString()
            binding.humidity.text= it.body()?.main?.humidity.toString()

            binding.sunrise.text= it.body()?.sys?.sunrise.toString()
            binding.sunset.text= it.body()?.sys?.sunset.toString()

            binding.wind.text= it.body()?.wind?.speed.toString()


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
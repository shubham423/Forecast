package com.example.weatherforecast.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.weatherforecast.databinding.FragmentHomeBinding
import com.example.weatherforecast.util.Resource
import com.example.weatherforecast.util.getIconResources
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by activityViewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var hourlyWeatherAdapter: HourlyWeatherAdapter

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
        viewModel.getWeatherDataByLatLong("28.6894", "77.2819", "standard")
        viewModel.getWeatherDataByCityName("delhi")
        initObservers()

    }

    private fun initObservers() {
        viewModel.currentWeatherResponse.observe(viewLifecycleOwner) {

            when (it) {
                is Resource.Success -> {
                    it.apply {
                        binding.weatherInText.text = data?.name
                        binding.dateText.text = currentSystemTime()
                        binding.weatherTemperature.text =
                            data?.main?.temp?.minus(273)?.toInt()?.let { it1 -> getTemp(it1) }
                        binding.weatherMinMax.text =
                            data?.main?.tempMax?.minus(273)?.let { it1 -> getTemp(it1.toInt()) } + "/" + data?.main?.tempMin?.minus(
                                273
                            )?.let { it1 ->
                                getTemp(
                                    it1.toInt()
                                )
                            }
                        binding.weatherMain.text = data?.weather?.get(0)?.description
                        binding.humidityText.text = data?.main?.humidity.toString() + "%"
                        binding.pressureText.text = data?.main?.pressure.toString() + "hPa"
                        binding.windSpeedText.text = data?.wind?.speed.toString() + "m/s"
                        context?.let {
                            binding.weatherIcon.getIconResources(
                                it,
                                data?.weather?.get(0)?.description
                            )
                        }
                    }

                }
                is Resource.Loading -> {
                    Log.d("requireActivity()", "inside loading")

                }
                is Resource.Error -> {
                    Log.d("requireActivity()", "inside error")
                }
            }
        }
    }

    private fun updateLocation(city: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = city
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = "Today"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun getTemp(temp: Int): String {

        return temp?.toString() + "°C"

    }

    fun currentSystemTime(): String {
        val currentTime = System.currentTimeMillis()
        val date = Date(currentTime)
        val dateFormat = SimpleDateFormat("EEEE MMM d, hh:mm aaa")
        return dateFormat.format(date)
    }


}

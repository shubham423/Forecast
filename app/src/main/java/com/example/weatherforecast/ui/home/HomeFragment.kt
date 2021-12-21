package com.example.weatherforecast.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.weatherforecast.R
import com.example.weatherforecast.databinding.FragmentHomeBinding
import com.example.weatherforecast.util.Resource
import com.example.weatherforecast.util.getDateTime
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
        viewModel.getWeatherDataByCityName("delhi")
        setHasOptionsMenu(true)
        initObservers()

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        val inflater: MenuInflater = inflater
        inflater.inflate(R.menu.settings_menu, menu)

        val search = menu.findItem(R.id.appSearchBar)
        val searchView = search.actionView as SearchView

        val searchItem: MenuItem = menu.findItem(R.id.appSearchBar)
        if (searchItem != null) {
           searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.d("query", "query submit called")
                    if (query != null) {
                        viewModel.getWeatherDataByCityName(query)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    Log.d("query", "query change called")
                    return true
                }
            })
        }
    }

        private fun initObservers() {
            viewModel.weatherResponse.observe(viewLifecycleOwner) {

                when (it) {
                    is Resource.Success -> {
                        it.apply {
                            binding.weatherInText.text = data?.name
                            binding.dateText.text = currentSystemTime()
                            binding.weatherTemperature.text = data?.main?.temp?.toString() + "°C"
                            binding.weatherMinMax.text =
                                data?.main?.tempMin.toString() + "°C" + "/" + data?.main?.tempMax.toString() + "°C"
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

    fun currentSystemTime(): String {
        val currentTime = System.currentTimeMillis()
        val date = Date(currentTime)
        val dateFormat = SimpleDateFormat("EEEE MMM d, hh:mm aaa")
        return dateFormat.format(date)
    }
    }

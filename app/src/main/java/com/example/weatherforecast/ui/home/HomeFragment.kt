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
                        viewmodel.getWeatherDataByCityName(query)
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
            viewmodel.weatherResponse.observe(viewLifecycleOwner) {

                when (it) {
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        it.data?.let { it1 -> updateLocation(it1.name) }
                        Log.d("HomeFragment", "${it.data}")
                        binding.address.text = it.data?.name + "," + (it.data?.sys?.country ?: "")

                        binding.temp.text = it.data?.main?.temp?.minus(273.15)?.toInt().toString() + "°C"
                        binding.status.text = it.data?.weather?.get(0)?.description ?: ""
                        binding.updatedAt.text = "Updated at: " + SimpleDateFormat(
                            "dd/MM/yyyy hh:mm a",
                            Locale.ENGLISH
                        ).format(
                            (it.data?.dt)?.times(1000)?.toLong()?.let { it1 ->
                                Date(
                                    it1
                                )
                            })

                        binding.tempMin.text = "Min Temp: " + (it.data?.main?.tempMin?.minus(272)?.toInt() ?: "") + "°C"
                        binding.tempMax.text = "Max Temp: " + (it.data?.main?.tempMax?.minus(273)?.toInt() ?: "") + "°C"
                        binding.pressure.text = it.data?.main?.pressure.toString()
                        binding.humidity.text = it.data?.main?.humidity.toString()

                        binding.sunrise.text = getDateTime(it.data?.sys?.sunrise.toString())
                        binding.sunset.text = getDateTime(it.data?.sys?.sunset.toString())

                        binding.wind.text = it.data?.wind?.speed.toString()


                    }
                    is Resource.Loading -> {
                        Log.d("requireActivity()", "inside loading")
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Error -> {
                        Log.d("requireActivity()", "inside error")
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        }

        private fun updateLocation(city: String) {
            (activity as? AppCompatActivity)?.supportActionBar?.title = city
            (activity as? AppCompatActivity)?.supportActionBar?.subtitle = "Today"
        }

    private fun getDateTime(s: String): String? {
        try {
            val sdf = SimpleDateFormat("MM/dd/yyyy")
            val netDate = Date(s.toLong() * 1000)
            return sdf.format(netDate)
        } catch (e: Exception) {
            return e.toString()
        }
    }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }

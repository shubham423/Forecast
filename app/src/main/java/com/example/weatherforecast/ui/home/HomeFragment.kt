package com.example.weatherforecast.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewmodel.getWeatherDataByCityName(query)
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        initObservers()
    }

    private fun initObservers() {
        viewmodel.weatherResponse.observe(viewLifecycleOwner){

            when(it){
                is Resource.Success -> {
                    binding.progressBar.visibility=View.GONE
                    it.data?.let { it1 -> updateLocation(it1.name) }
                    Log.d("HomeFragment","${it.data}")
                    binding.address.text= it.data?.name +","+ (it.data?.sys?.country ?: "")
                    binding.temp.text= it.data?.main?.temp.toString()+"°F"
                    binding.status.text= it.data?.weather?.get(0)?.description ?: ""
                    binding.updatedAt.text="Updated at: "+ SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(
                        (it.data?.dt)?.times(1000)?.toLong()?.let { it1 ->
                            Date(
                                it1
                            )
                        })

                    binding.tempMin.text="Min Temp: " + (it.data?.main?.tempMin ?: "") +"°C"
                    binding.tempMax.text="Max Temp: " + (it.data?.main?.tempMax ?: "") +"°C"
                    binding.pressure.text= it.data?.main?.pressure.toString()
                    binding.humidity.text= it.data?.main?.humidity.toString()

                    binding.sunrise.text= it.data?.sys?.sunrise.toString()
                    binding.sunset.text= it.data?.sys?.sunset.toString()

                    binding.wind.text= it.data?.wind?.speed.toString()


                }
                is Resource.Loading -> {
                    Log.d("requireActivity()","inside loading")
                    binding.progressBar.visibility=View.VISIBLE
                }
                is Resource.Error -> {
                    Log.d("requireActivity()","inside error")
                    binding.progressBar.visibility=View.GONE
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
}
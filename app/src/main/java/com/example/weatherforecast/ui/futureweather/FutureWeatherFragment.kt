package com.example.weatherforecast.ui.futureweather

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.weatherforecast.databinding.FragmentFutureWeatherBinding
import com.example.weatherforecast.ui.home.HomeViewModel
import com.example.weatherforecast.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FutureWeatherFragment : Fragment() {

    private val viewmodel: HomeViewModel by activityViewModels()
    private var _binding: FragmentFutureWeatherBinding? = null

    private lateinit var futureWeatherAdapter: FutureWeatherAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFutureWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.getWeatherDataByCityName("delhi")
        initObservers()
    }

    private fun initObservers() {
        viewmodel.weatherResponse.observe(viewLifecycleOwner){
            when(it){
                is Resource.Success -> {
                    Log.d("requireActivity()","${it.data?.weather}")
                    futureWeatherAdapter=FutureWeatherAdapter()
                    it.data?.weather?.let { it1 -> futureWeatherAdapter.setData(it1) }
                    binding.recyclerView.adapter=futureWeatherAdapter
                }
                is Resource.Loading -> {
                }

                is Resource.Error -> { }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
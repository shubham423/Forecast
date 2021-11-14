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
            binding.address.text= it.body()?.name ?: ""
            binding.temp.text= it.body()?.main?.temp.toString()
            binding.status.text= it.body()?.weather?.get(0)?.description ?: ""

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
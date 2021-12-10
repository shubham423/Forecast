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
        initObservers()
    }

    private fun initObservers() {
        Log.d("requireActivity()","inside init observer")
        viewmodel.weeklyWeatherResponse.observe(viewLifecycleOwner){

            when(it){
                is Resource.Success -> {
                    binding.progressBar2.visibility=View.GONE
                    futureWeatherAdapter=FutureWeatherAdapter()
                    it.data?.let { it1 -> futureWeatherAdapter.setData(it1.daily) }
                    binding.recyclerView.adapter=futureWeatherAdapter
                }
                is Resource.Loading -> {
                    binding.progressBar2.visibility=View.VISIBLE
                    Log.d("requireActivity()","inside loading")
                }

                is Resource.Error -> {
                    binding.progressBar2.visibility=View.GONE
                    Log.d("requireActivity()","inside error")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
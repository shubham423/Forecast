package com.example.weatherforecast.ui.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherforecast.R
import com.example.weatherforecast.databinding.FragmentSettingsBinding
import com.example.weatherforecast.util.TempDisplaySetting
import com.example.weatherforecast.util.TempDisplaySettingManager


class SettingsFragment : Fragment() {

    private lateinit var binding:FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSettingsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tempDisplaySettingManager=TempDisplaySettingManager(requireContext())
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
             when(checkedId){
                R.id.radioButton_c -> {
                    tempDisplaySettingManager.updateSetting(TempDisplaySetting.Celsius)
                }
                R.id.radioButton_f -> {
                    tempDisplaySettingManager.updateSetting(TempDisplaySetting.Fahrenheit)
                }

                else -> {

                }
            }
        }
    }

}
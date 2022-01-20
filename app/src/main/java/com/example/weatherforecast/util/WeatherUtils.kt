package com.example.weatherforecast.util

import android.content.Context
import android.content.DialogInterface
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.weatherforecast.R
import com.github.pwittchen.weathericonview.WeatherIconView
import java.text.SimpleDateFormat
import java.util.*

fun showTempDisplaySettingDialog(context: Context) {
    val dialogBuilder = AlertDialog.Builder(context)
        .setTitle("Choose Display Units")
        .setMessage("Choose which temperature unit to use for temperature display")
        .setPositiveButton("F°", object: DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                //todo
            }
        })
        .setNeutralButton("C°") { _,_ ->
            //todo
        }
        .setOnDismissListener {
            Toast.makeText(context, "Setting will take affect on app restart", Toast.LENGTH_SHORT).show()
        }

    dialogBuilder.show()
}

fun WeatherIconView.getIconResources(context: Context, condition: String?) {
    if (condition != null) {
        when {
            condition.contains("rain", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_rain))
            }
            condition.contains("snow", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_snow))
            }
            condition.contains("sun", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_day_sunny))
            }
            condition.contains("cloud", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_forecast_io_cloudy))
            }
            condition.contains("Clear", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_wu_clear))
            }
            condition.contains("Overcast", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_day_sunny_overcast))
            }
            condition.contains("sleet", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_day_sleet_storm))
            }
            condition.contains("Mist", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_fog))
            }
            condition.contains("drizzle", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_raindrops))
            }
            condition.contains("thunderstorm", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_wu_tstorms))
            }
            condition.contains("Thunder", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_thunderstorm))
            }
            condition.contains("Cloudy", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_forecast_io_cloudy))
            }
            condition.contains("Fog", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_forecast_io_fog))
            }
            condition.contains("Sunny", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_wu_mostlysunny))
            }
            condition.contains("Blizzard", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_snow_wind))
            }
            condition.contains("Ice", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_wu_chancesnow))
            }
            condition.contains("ice", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_forecast_io_snow))
            }
            condition.contains("Rain", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_rain_wind))
            }
            condition.contains("wind", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_windy))
            }
            condition.contains("Wind", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_strong_wind))
            }
            condition.contains("storm", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_storm_warning))
            }
            condition.contains("Storm", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_forecast_io_thunderstorm))
            }
            condition.contains("thunder", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_day_snow_thunderstorm))
            }
            else -> {
                this.setIconResource(context.getString(R.string.wi_forecast_io_partly_cloudy_day))
            }
        }
    }

}
fun formatTempForDisplay(temp: Float, tempDisplaySetting: TempDisplaySetting) : String {
    return when (tempDisplaySetting) {
        TempDisplaySetting.Fahrenheit -> String.format("%.2f F°", temp)
        TempDisplaySetting.Celsius -> {
            val temp = (temp - 32f) * (5f/9f)
            String.format("%.2f C°", temp)
        }
    }
}

enum class TempDisplaySetting {
    Fahrenheit, Celsius
}
class TempDisplaySettingManager(context: Context) {
    private val preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    fun updateSetting(setting: TempDisplaySetting) {
        preferences.edit().putString("key_temp_display", setting.name).commit()
    }

    fun getTempDisplaySetting() : TempDisplaySetting {
        val settingValue = preferences.getString("key_temp_display", TempDisplaySetting.Fahrenheit.name) ?: TempDisplaySetting.Fahrenheit.name
        return TempDisplaySetting.valueOf(settingValue)
    }
}

fun getDateTimeFormatted(s: String): String? {
    return try {
        val sdf = SimpleDateFormat("MM/dd/yyyy")
        val netDate = Date(s.toLong() * 1000)
        sdf.format(netDate)
    } catch (e: Exception) {
        e.toString()
    }
}

fun convertLongToTime(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("yyyy.MM.dd HH:mm")
    return format.format(date)
}

fun getTemp(temp: Int): String {
    return temp?.toString() + "°C"
}
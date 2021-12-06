package com.example.weatherforecast.util

import android.content.Context
import android.content.DialogInterface
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

 fun getDateTime(s: String): String? {
    try {
        val sdf = SimpleDateFormat("MM/dd/yyyy")
        val netDate = Date(s.toLong() * 1000)
        return sdf.format(netDate)
    } catch (e: Exception) {
        return e.toString()
    }
}
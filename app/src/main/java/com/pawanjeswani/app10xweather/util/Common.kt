package com.pawanjeswani.app10xweather.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import java.text.SimpleDateFormat
import java.util.*

fun Context.isConnected(): Boolean {

    val hasInternet: Boolean

    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        hasInternet = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    } else {
        hasInternet = try {
            if (connectivityManager.activeNetworkInfo == null) {
                false
            } else {
                connectivityManager.activeNetworkInfo?.isConnected!!
            }
        } catch (e: Exception) {
            false
        }
    }
    return hasInternet
}

fun View.getDayOfWeek(timeInMillis: Long, cal:Calendar): String {
    val date: Date = cal.time
    date.time = timeInMillis
    return SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.time)
}

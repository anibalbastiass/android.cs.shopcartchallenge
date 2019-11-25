package com.anibalbastias.android.shopcart.presentation.service.connection

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import com.anibalbastias.android.shopcart.presentation.util.CheckInternetConnectionListener

class InternetCheckReceiver : BroadcastReceiver() {

    var callback: CheckInternetConnectionListener? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        val isConnected = activeNetwork.isConnected
        callback?.onChangeInternetConnection(isConnected)
    }
}
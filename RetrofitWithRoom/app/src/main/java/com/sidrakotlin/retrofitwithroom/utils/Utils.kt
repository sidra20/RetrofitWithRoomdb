package com.sidrakotlin.retrofitwithroom.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class Utils {

    companion object{
        fun isInternetAvail(context: Context):Boolean{
            (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run{
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    return this.getNetworkCapabilities(this.activeNetwork)?.hasCapability(
                        NetworkCapabilities.NET_CAPABILITY_INTERNET
                    ) ?: false
                }
                else{
                    (@Suppress("Deprication")
                            return this.activeNetworkInfo?.isConnected ?: false)
                }
            }
        }
    }
}
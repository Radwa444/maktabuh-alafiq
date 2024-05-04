package com.example.maktabuhalafiq.utils

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.HiltAndroidApp
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {

        super.onCreate()
        listenToNetworkConnectivity()
    }

    @SuppressLint("CheckResult")
    private fun listenToNetworkConnectivity() {
        ReactiveNetwork
            .observeInternetConnectivity()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { isConnected: Boolean ->
                Log.d(TAG,"Connected to internet: $isConnected")
                FirebaseCrashlytics.getInstance().setCustomKey("connected_to_internet", isConnected)
            }
    }
    companion object{
        private const val TAG="MyApplication"
    }
}

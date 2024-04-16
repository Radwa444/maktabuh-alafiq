package com.example.maktabuhalafiq.utils

import android.annotation.SuppressLint
import android.app.Application

import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.HiltAndroidApp // استيراد التعليمة HiltAndroidApp
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

@HiltAndroidApp
class MyApplication : Application() {
    private var networkDisposable: Disposable? = null

    override fun onCreate() {

        super.onCreate()
       // listenToNetworkConnectivity()
    }

//    @SuppressLint("CheckResult")
//    private fun listenToNetworkConnectivity() {
//        ReactiveNetwork
//            .observeInternetConnectivity()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe { isConnected: Boolean ->
//                FirebaseCrashlytics.getInstance().setCustomKey("connected_to_internet", isConnected)
//            }
//    }
}

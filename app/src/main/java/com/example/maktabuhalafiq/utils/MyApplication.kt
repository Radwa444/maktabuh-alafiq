package com.example.maktabuhalafiq.utils

import android.annotation.SuppressLint
import android.app.Application
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.google.firebase.crashlytics.FirebaseCrashlytics
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

//manage the application lifecycle
class MyApplication :Application(){
    private var networkDisposable: Disposable? = null


    override fun onCreate() {
     listenToNetworkConnectivity()
        super.onCreate()

    }


    @SuppressLint("CheckResult")
    fun listenToNetworkConnectivity(){
        ReactiveNetwork
            .observeInternetConnectivity()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ isConnected:Boolean ->
                FirebaseCrashlytics.getInstance().setCustomKey("oonnected_to_internet",isConnected)

            }

}}
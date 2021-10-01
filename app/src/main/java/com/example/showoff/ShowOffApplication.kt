package com.example.showoff

import android.app.Application
import android.util.Log
import com.example.showoff.DaggerClasses.DaggerNetworkComponent
import com.example.showoff.Singleton.DataSingleton
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

class ShowOffApplication : Application() {

    @Inject
    lateinit var retrofit : Retrofit

    @Inject
    lateinit var dataSingleton: DataSingleton

    init {
        instance = this
        var daggerNetworkComponent = DaggerNetworkComponent.create()
        daggerNetworkComponent.inject(this)
    }


    companion object {
        private var instance: ShowOffApplication? = null
        var TAG:String = "ShowOffApplication"

        fun applicationContext() : ShowOffApplication {
            Log.d(TAG, "applicationContext: ")
            return instance as ShowOffApplication
        }

    }

}
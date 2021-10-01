package com.example.showoff.Retrofit

import com.example.showoff.model.ShopItems
import dagger.internal.InjectedFieldSignature
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit
import javax.inject.Inject


interface Api {
    @GET("products")
    fun getProducts(): Call<List<ShopItems>>

    @GET("products/categories")
    fun getCategories():Call<List<String>>

    @GET("products/{id}")
    fun getProductDetails(@Path("id") version:String):Call<ShopItems>

    companion object {

        var retrofitService: Api? = null

        fun getInstance(retrofit: Retrofit): Api {
            if (retrofitService == null) {
                retrofitService = retrofit.create(Api::class.java)
            }
            return retrofitService!!
        }
    }
}
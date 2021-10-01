package com.example.showoff.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.showoff.Retrofit.Api
import com.example.showoff.ShowOffApplication
import com.example.showoff.model.ShopItems
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class APIViewModel : ViewModel( ) {

    var api:Api

    init {
         api = Api.getInstance(ShowOffApplication.applicationContext().retrofit)

    }

    var shopItems = MutableLiveData<List<ShopItems>>()
    var productCategory = MutableLiveData<List<String>>()
    var productDetails = MutableLiveData<ShopItems>()

    fun getProducts()
    {
        val response = api.getProducts()
        response?.enqueue(object : Callback<List<ShopItems>> {
            override fun onResponse(
                call: Call<List<ShopItems>>,
                response: Response<List<ShopItems>>
            ) {
                Log.d("viewmodel", "onResponse: ----\n"+response.body())

                shopItems.postValue(response.body() as ArrayList<ShopItems>?)
            }

            override fun onFailure(call: Call<List<ShopItems>>, t: Throwable) {
                Log.d("viewmodel", "error: ----\n"+ t.toString())
            }
        })
    }

    fun getCategories()
    {
        val response = api.getCategories()
        Log.d("viewmodel", "getCategories: "+response)
        response?.enqueue(object : Callback<List<String>> {
            override fun onResponse(
                call: Call<List<String>>,
                response: Response<List<String>>
            ) {
                Log.d("viewmodel", "onResponse: ----\n"+response.body())
                productCategory.postValue(response.body())
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Log.d("viewmodel", "error: ----\n"+ t.toString())
            }
        })
    }

    fun getProductDetails(id:String)
    {
        val response = api.getProductDetails(id)
        Log.d("viewmodel", "getCategories: "+response)
        response?.enqueue(object : Callback<ShopItems> {
            override fun onResponse(
                call: Call<ShopItems>,
                response: Response<ShopItems>
            ) {
                Log.d("viewmodel", "onResponse: ----\n"+response.body())
                productDetails.postValue(response.body())
            }

            override fun onFailure(call: Call<ShopItems>, t: Throwable) {
                Log.d("viewmodel", "error: ----\n"+ t.toString())
            }
        })


    }

}


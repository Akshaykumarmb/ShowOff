package com.example.showoff.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.showoff.R
import com.example.showoff.ViewModel.APIViewModel

class ProductDetails : AppCompatActivity() {

    var TAG = "ProductDetails"
    private lateinit var apiViewModel: APIViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        setTitle("Details")

        var id = intent.getStringExtra("P_ID")

        apiViewModel = ViewModelProvider(this).get(APIViewModel::class.java)

        apiViewModel.getProductDetails(id!!)

        apiViewModel.productDetails.observe(this, {
            Log.d(TAG, "onCreate: product "+it.title)
            setTitle(it.title)
        });


    }
}
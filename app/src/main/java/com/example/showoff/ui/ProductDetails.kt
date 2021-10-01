package com.example.showoff.ui

import android.graphics.pdf.PdfRenderer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.showoff.Listners.RecyclerViewClickListener
import com.example.showoff.R
import com.example.showoff.ShowOffApplication
import com.example.showoff.ViewModel.APIViewModel
import com.example.showoff.adapters.PageIndicator
import com.example.showoff.adapters.ProductDetailsImageAdapter
import com.example.showoff.model.ShopItems
import kotlinx.android.synthetic.main.activity_product_details.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class ProductDetails : AppCompatActivity(), RecyclerViewClickListener {

    var TAG = "ProductDetails"
    private lateinit var apiViewModel: APIViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var productDetailsImagesAdapter:ProductDetailsImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        title="Details"

        val id = intent.getStringExtra("P_ID")

        recyclerView = product_detail_images
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        PagerSnapHelper().attachToRecyclerView(recyclerView)
        recyclerView.addItemDecoration(PageIndicator())

        apiViewModel = ViewModelProvider(this).get(APIViewModel::class.java)

        apiViewModel.getProductDetails(id!!)

        apiViewModel.productDetails.observe(this, {
            Log.d(TAG, "onCreate: product "+it.title)
            title=it.title
            var imageList:ArrayList<String> = ArrayList()
            imageList.add(it.image)
            imageList.add(it.image)
            imageList.add(it.image)
            productDetailsImagesAdapter = ProductDetailsImageAdapter(ShowOffApplication.applicationContext(), imageList, this)
            product_detail_images.adapter = productDetailsImagesAdapter
        })


    }

    override fun recyclerViewListClicked(product: ShopItems) {
        TODO("Not yet implemented")
    }

    override fun recyclerViewListLongClicked(product: ShopItems) {
        TODO("Not yet implemented")
    }
}


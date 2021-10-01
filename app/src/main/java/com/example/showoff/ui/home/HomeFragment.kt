package com.example.showoff.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.showoff.Listners.RecyclerViewClickListener
import com.example.showoff.R
import com.example.showoff.ShowOffApplication
import com.example.showoff.ViewModel.APIViewModel
import com.example.showoff.adapters.ProductAdapter
import com.example.showoff.model.ShopItems
import com.example.showoff.ui.ProductDetails
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment(), RecyclerViewClickListener {

    private val TAG = "HomeFragment"
    private lateinit var apiViewModel: APIViewModel
    private lateinit var productAdapter: ProductAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        apiViewModel =
                ViewModelProvider(this).get(APIViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = root.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
//        PagerSnapHelper().attachToRecyclerView(recyclerView)
        apiViewModel.getProducts()

        apiViewModel.shopItems.observe(viewLifecycleOwner, {
            val productList = it
            Log.d(TAG, "onCreateView: first one ---" + productList.size)

            progress_home.visibility = View.GONE
            if (it != null && it.isNotEmpty()) {
                productAdapter = ProductAdapter(ShowOffApplication.applicationContext(), it, this)
                recyclerView.adapter = productAdapter
            }
        })
        return root
    }

    override fun recyclerViewListClicked(product: ShopItems) {
        Log.d(TAG, "recyclerViewListClicked: " + product.id)
        val intent = Intent(activity, ProductDetails::class.java)
        intent.putExtra("P_ID", product.id.toString())
        startActivity(intent)
    }

    override fun recyclerViewListLongClicked(product: ShopItems) {
        Log.d(TAG, "recyclerViewListLongClicked: ")
    }
}
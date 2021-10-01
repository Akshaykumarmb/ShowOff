package com.example.showoff.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.showoff.Listners.RecyclerViewClickListener
import com.example.showoff.R
import com.example.showoff.model.ShopItems
import kotlinx.android.synthetic.main.nav_header_main.view.*
import kotlinx.android.synthetic.main.product_row.view.*

class ProductDetailsImageAdapter (context: Context, productsImageList:List<String>, listclicklistner: RecyclerViewClickListener): RecyclerView.Adapter<ProductDetailsImageAdapter.ViewHolder>(){
    var context = context
    var productsImageList = productsImageList
    var listclicklistner = listclicklistner

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductDetailsImageAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.product_image_row, parent, false)

        return ProductDetailsImageAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductDetailsImageAdapter.ViewHolder, position: Int) {
        Glide.with(context)
                .load(productsImageList.get(position))
                .into(holder.itemView.product_immage)

    }

    override fun getItemCount(): Int {
        return productsImageList.size
    }


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val view: View =itemView
    }

}
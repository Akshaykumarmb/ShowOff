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
import kotlinx.android.synthetic.main.product_row.view.*

class ProductAdapter(context:Context,products:List<ShopItems>,listclicklistner:RecyclerViewClickListener): RecyclerView.Adapter<ProductAdapter.ViewHolder>(){

    var productList:List<ShopItems> = emptyList()
    var context: Context? = null
    var listclicklistner:RecyclerViewClickListener
    init {
       this.productList = products
        this.context = context
        this.listclicklistner =listclicklistner
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.product_row, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(productList.get(position).image)
            .into(holder.productImage)
        holder.productDesc.text = productList.get(position).description
        holder.productPrice.text = productList.get(position).price.toString()  + context?.resources?.getString(R.string.rs)
        holder.productTitle.text = productList.get(position).title
        holder.view.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                listclicklistner.recyclerViewListClicked(productList.get(position))
            }
        })
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val view:View =itemView
        val productTitle: TextView = view.product_title
        val productImage :ImageView = view.product_immage
        val productDesc:TextView = view.product_desc
        val productPrice:TextView = view.product_price
    }
}
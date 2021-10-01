package com.example.showoff.Listners

import com.example.showoff.model.ShopItems


interface RecyclerViewClickListener {

    fun recyclerViewListClicked(product:ShopItems)

    fun recyclerViewListLongClicked(product:ShopItems)

}
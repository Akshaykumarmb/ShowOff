package com.example.showoff.model

data class Rating (

    val rate : Double,
    val count : Int

)


data class ShopItems (

    val image : String,
    val price : Double,
    val rating : Rating,
    val description : String,
    val id : Int,
    val title : String,
    val category : String

)





package com.example.TokenSell

data class ItemModel(val image: Int, val title: String, val description: String, var quantity: Double = 0.0, var itemPrice: Double = 0.0, var taxPercent: Double = 8.0) {



}
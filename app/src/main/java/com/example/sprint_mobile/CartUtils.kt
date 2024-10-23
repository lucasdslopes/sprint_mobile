package com.example.sprint_mobile

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class CartUtils {


    fun saveCartProducts(context: Context, cartProducts: List<ProductResult>) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("CART_PREFS", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(cartProducts)
        editor.putString("cart_products", json)
        editor.apply()
    }

    fun getCartProducts(context: Context): List<ProductResult> {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("CART_PREFS", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("cart_products", null)
        val type = object : TypeToken<List<ProductResult>>() {}.type
        return if (json != null) {
            gson.fromJson(json, type)
        } else {
            emptyList()
        }
    }
}

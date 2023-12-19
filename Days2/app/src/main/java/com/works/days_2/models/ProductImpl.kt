package com.works.days_2.models

class ProductImpl : IProduct, IAddress {
    override fun productAdd(title: String, price: Int): Boolean {
        return true
    }

    override fun productList(): List<String> {
        val list = mutableListOf<String>()
        return list
    }

    override fun productSearch(q: String): List<String> {
        val list = mutableListOf<String>()
        return list
    }

    override fun getAddress(userID: Int): String {
        return "İstanbul"
    }
}
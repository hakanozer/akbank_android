package com.works.days_2.models

interface IProduct {

    fun productAdd( title: String, price: Int ) : Boolean
    fun productList( ) : List<String>
    fun productSearch( q: String ) : List<String>

}
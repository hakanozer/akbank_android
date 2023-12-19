package com.works.days_2.utils

import android.util.Log

//class Settings( val num1: Int )  {
class Settings {

    var num1 = 0
    var data = ""

    constructor() {

    }

    constructor(num1: Int) {
        this.num1 = num1
    }

    constructor(num1: Int, data: String) {
        this.num1 = num1
        this.data = data
    }


    fun fnc1( data:String, count: Int, extData:String? ) : Int? {
        var end = data.length + count + num1 + this.data.length
        extData?.let {
            end += it.length
        }
        Log.d("end", end.toString())
        if (end > 10) {
            return null
        }
        return end
    }

}
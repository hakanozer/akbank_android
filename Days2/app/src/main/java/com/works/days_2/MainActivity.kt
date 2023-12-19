package com.works.days_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // var, val
        // var -> değişken
        var name = "Serkan"
        name = "Erkan"
        val surname = "Bilmem"
        val address:String = ""
        val num = 10

        name.length
        // optional type
        var city:String? = ""
        //city = null
        val count = city?.length
        val countx = city!!.length
        city?.let {
            val count = it.length
        }

        val xx = city?.length ?: 1
        val dataCount = if (city == null) "" else ""

        // Tam sayı
        val num1:Byte = 127
        val num2:Short = 40
        val num3 = 100
        val num4:Long = 150

        // Ondalıklı değer
        val num5 = 10.5
        val num6 = 10.5f

        // Dizi
        val arr2 = arrayOf<String>("İstanbul", "İzmir", "Ankara", "Bursa")
        // arr2[0] = ""
        // arr2.set(0, "")
        for ( item in arr2 ) {
            Log.d("item", item)
        }




    }

}
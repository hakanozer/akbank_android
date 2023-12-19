package com.works.days_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.works.days_2.utils.EApp
import kotlin.math.log

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

        var start = System.currentTimeMillis()
        val arr = mutableListOf<String>()
        arr.add("Ali")
        arr.add("Zehra")
        arr.add("Zehra")
        arr.add("Ayşe")
        arr.add("Selin")
        arr.add("Selin")
        arr.add("Selin")
        arr.add("Ahmet")
        arr.add("Kemal")
        arr.add("Kemal")
        Log.d("arr", arr.toString())
        var end = System.currentTimeMillis()
        var bettween = end - start
        Log.d("mutableListOf", bettween.toString())

        // delete item
        //arr.removeAt(0)
        //arr.remove("ayşe")

        for ( i in 0..arr.count() - 1) {
            Log.d("i", arr.get(i))
        }



        // Set
        start = System.currentTimeMillis()
        val set = mutableSetOf<String>()
        set.add("Ali")
        set.add("Zehra")
        set.add("Zehra")
        set.add("Ayşe")
        set.add("Selin")
        set.add("Selin")
        set.add("Selin")
        set.add("Ahmet")
        set.add("Kemal")
        set.add("Kemal")
        Log.d("set", set.toString())
        end = System.currentTimeMillis()
        bettween = end - start
        Log.d("mutableSetOf", bettween.toString())

        // Map
        val map = mutableMapOf<String, Any>()
        map.put("city", "İstanbul")
        map.put("name", "Kemal")
        map.put("email", "zehra@mail.com")
        map.put("name", "Kenan")
        map.put("name", "Zehra")
        map.put("surname", "Bilmem")
        map.put("status", true)
        map.put("age", 35)
        Log.d("name", map.get("name").toString())
        Log.d("Map", map.toString())

        val keys = map.keys
        for (key in keys) {
            val data = map.get(key)
            Log.d("key", key)
            Log.d("val", data.toString())
            if (data is Int) {
                val numConvert = data.toInt()
                Log.d("numConvert", "${numConvert + 10}")
            }
        }

        for ( (k, v) in map ) {
            Log.d(k, v.toString())
        }

        val map1 = mutableMapOf<EApp, Any>()
        map1.put(EApp.NAME, "Sultan")
        map1.put(EApp.SURNAME, "Bil")
        map1.put(EApp.AGE, 30)
        Log.d("EApp", map1.toString())


    }

}
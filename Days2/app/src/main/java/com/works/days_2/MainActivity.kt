package com.works.days_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.works.days_2.datas.Category
import com.works.days_2.datas.Product
import com.works.days_2.models.IAddress
import com.works.days_2.models.IProduct
import com.works.days_2.models.ProductImpl
import com.works.days_2.useAbstract.Customer
import com.works.days_2.utils.EApp
import com.works.days_2.utils.Profile
import com.works.days_2.utils.Settings
import java.util.Scanner
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


        // OOP
        val settings = Settings(25, "newData")
        val countY = settings.fnc1("Kotlin", 10, "optional")
        countY?.let {
            if (it > 20) {

            }
        }

        val profile = Profile()
        val sum = profile.call()
        Log.d("Sum", sum.toString())

        val pro = ProductImpl()
        val pro1: IProduct = ProductImpl()
        val pro2: IAddress = ProductImpl()

        val customer = Customer(100)
        Log.d("name", customer.userName())
        Log.d("total", customer.userTotal().toString())

        val cat1 = Category(1, "Elektronik")
        val p1 = Product("TV", "TV Detail", 10000, cat1)

        val cat2 = Category(2, "Telefon")
        val p2 = Product("Phone", "Phone Detail", 20000, cat2)

        val list = mutableListOf<Product>()
        list.add(p1)
        list.add(p2)

        for(item in list) {
            Log.d("title", item.category.name)
        }


    }

}
package com.works.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.works.project.configs.ApiClient
import com.works.project.models.Product
import com.works.project.services.DummyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Timer
import java.util.TimerTask

class ProductDetail : AppCompatActivity() {

    lateinit var dummyService: DummyService
    lateinit var d_title: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        d_title = findViewById(R.id.d_title)

        val id = intent.getLongExtra("id", 0)
        if (id > 0) {

            dummyService = ApiClient().getClient().create(DummyService::class.java)
            dummyService.singleProduct(id).enqueue(object : Callback<Product> {
                override fun onResponse(call: Call<Product>, response: Response<Product>) {
                    val status = response.code()
                    if (status == 200) {
                        val data = response.body()
                        data?.let {
                            Log.d("detail", it.toString())
                        }
                    }
                }

                override fun onFailure(call: Call<Product>, t: Throwable) {
                    Snackbar.make(d_title, t.toString(), Snackbar.LENGTH_LONG)
                }
            })

        }else {
            Snackbar.make(d_title, "Product ID Fail!", Snackbar.LENGTH_LONG).show()
            Timer().schedule(object :  TimerTask() {
                override fun run() {
                    finish()
                }
            }, 3000)

        }

    }

}
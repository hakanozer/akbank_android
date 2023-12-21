package com.works.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import com.works.project.configs.ApiClient
import com.works.project.models.Products
import com.works.project.services.DummyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductActivity : AppCompatActivity() {

    lateinit var dummyService: DummyService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        dummyService = ApiClient().getClient().create(DummyService::class.java)
        dummyService.allProduct().enqueue(object : Callback<Products> {
            override fun onResponse(call: Call<Products>, response: Response<Products>) {
                val status = response.code()
                if (status == 200) {
                    val data = response.body()
                    data?.let {
                        Log.d("product", it.toString())
                    }
                }
            }

            override fun onFailure(call: Call<Products>, t: Throwable) {
                Snackbar.make(contentScene.sceneRoot, t.toString(), Snackbar.LENGTH_LONG)
            }
        })

    }

}
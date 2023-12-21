package com.works.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import com.google.android.material.snackbar.Snackbar
import com.works.project.adapters.ProductAdapter
import com.works.project.configs.ApiClient
import com.works.project.models.Product
import com.works.project.models.Products
import com.works.project.services.DummyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductActivity : AppCompatActivity() {

    lateinit var dummyService: DummyService
    lateinit var listView: ListView
    var arr = listOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        listView = findViewById(R.id.listView)

        dummyService = ApiClient().getClient().create(DummyService::class.java)
        dummyService.allProduct().enqueue(object : Callback<Products> {
            override fun onResponse(call: Call<Products>, response: Response<Products>) {
                val status = response.code()
                if (status == 200) {
                    val data = response.body()
                    data?.let {
                        this@ProductActivity.arr = it.products
                        val adapter = ProductAdapter(this@ProductActivity, it.products)
                        listView.adapter = adapter
                    }
                }
            }

            override fun onFailure(call: Call<Products>, t: Throwable) {
                Snackbar.make(contentScene.sceneRoot, t.toString(), Snackbar.LENGTH_LONG)
            }
        })


        // item click listener
        listView.setOnItemClickListener { parent, view, position, id ->
            val item = arr.get(position)
            val intent = Intent(this@ProductActivity, ProductDetail::class.java)
            intent.putExtra("id", item.id)
            startActivity(intent)
        }


    }

}
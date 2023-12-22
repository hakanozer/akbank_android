package com.works.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import com.works.project.adapters.DB
import com.works.project.adapters.ProductAdapter
import com.works.project.configs.ApiClient
import com.works.project.models.Product
import com.works.project.services.DummyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Likes : AppCompatActivity() {

    lateinit var l_listView: ListView
    lateinit var db: DB
    lateinit var dummyService: DummyService
    val arr = mutableListOf<Product>()
    lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_likes)

        l_listView = findViewById(R.id.l_listView)
        productAdapter = ProductAdapter(this, arr)
        l_listView.adapter = productAdapter
        dummyService = ApiClient().getClient().create(DummyService::class.java)
        db = DB(this)


        l_listView.setOnItemClickListener { parent, view, position, id ->
            val item = arr.get(position)
            val intent = Intent(this, ProductDetail::class.java)
            intent.putExtra("id", item.id)
            startActivity(intent)
        }
    }

    fun allLikes() {
        arr.clear()
        db.allLikes().forEach {
            dummyService.singleProduct(it).enqueue(object: Callback<Product> {
                override fun onResponse(call: Call<Product>, response: Response<Product>) {
                    val status = response.code()
                    if (status == 200) {
                        val data = response.body()
                        data?.let {
                            arr.add(it)
                            productAdapter.notifyDataSetChanged()
                        }
                    }
                }
                override fun onFailure(call: Call<Product>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
    }

    override fun onResume() {
        super.onResume()
        allLikes()
    }

}
package com.works.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.works.project.adapters.DB
import com.works.project.configs.ApiClient
import com.works.project.models.Product
import com.works.project.services.DummyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Timer
import java.util.TimerTask

class ProductDetail : AppCompatActivity() {

    lateinit var db: DB
    var item: Product? = null

    lateinit var dummyService: DummyService
    lateinit var d_title: TextView
    lateinit var d_img: ImageView
    lateinit var d_rating: RatingBar
    lateinit var d_price: TextView
    lateinit var d_detail: TextView
    lateinit var progressBar: ProgressBar
    lateinit var btn_like: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        db = DB(this)
        d_title = findViewById(R.id.d_title)
        d_img = findViewById(R.id.d_img)
        d_rating = findViewById(R.id.d_rating)
        d_price = findViewById(R.id.d_price)
        d_detail = findViewById(R.id.d_detail)
        progressBar = findViewById(R.id.progressBar)
        btn_like = findViewById(R.id.btn_like)
        btn_like.setOnClickListener(addLikeClick)

        val id = intent.getLongExtra("id", 0)
        if (id > 0) {

            dummyService = ApiClient().getClient().create(DummyService::class.java)
            dummyService.singleProduct(id).enqueue(object : Callback<Product> {
                override fun onResponse(call: Call<Product>, response: Response<Product>) {
                    val status = response.code()
                    if (status == 200) {
                        val data = response.body()
                        data?.let {
                            item = it
                            d_title.setText(it.title)
                            Glide.with(this@ProductDetail).load(it.thumbnail).into(d_img)
                            d_rating.rating = it.rating.toFloat()
                            d_price.setText("${it.price}₺")
                            d_detail.setText(it.description)
                            progressBar.visibility = View.INVISIBLE
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


    // add like click
    val addLikeClick = View.OnClickListener {
        val view = it
        item?.let {
            val status = db.addLike(it.id)
            if (status > -1) {
                Snackbar.make(view, "Like Add Success", Snackbar.LENGTH_LONG).show()
            }else {
               val deleteStatus = db.removeLike(it.id)
                if (deleteStatus > 0) {

                }
            }
        }
    }

}
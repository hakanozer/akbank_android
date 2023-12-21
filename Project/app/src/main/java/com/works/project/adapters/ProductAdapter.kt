package com.works.project.adapters

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.works.project.R
import com.works.project.models.Product

class ProductAdapter( private val activity: Activity, private val list: List<Product>)
    : ArrayAdapter<Product>(activity, R.layout.product_item, list )
{

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rootView = activity.layoutInflater.inflate(R.layout.product_item, null, true)
        val item = list.get(position)
        val t_title = rootView.findViewById<TextView>(R.id.r_title)
        val t_price = rootView.findViewById<TextView>(R.id.r_price)
        val t_image = rootView.findViewById<ImageView>(R.id.r_image)
        t_title.setText(item.title)
        t_price.setText("${item.price}₺")
        Glide.with(rootView).load(item.thumbnail).into(t_image)
        return rootView
    }

}
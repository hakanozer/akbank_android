package com.works.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.works.project.models.Customer

class SingUp : AppCompatActivity() {

    lateinit var s_txtTitle: TextView

    // Static Create
    companion object {
        var customerItem: Customer? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sing_up)

        s_txtTitle = findViewById(R.id.s_txtTitle)

        val title = intent.getStringExtra("title")
        title?.let {
            s_txtTitle.setText(it)
        }

        customerItem?.let {
            Log.d("customer", it.toString())
        }

    }

}
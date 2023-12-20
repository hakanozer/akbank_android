package com.works.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.works.project.configs.Util

class Dashboard : AppCompatActivity() {

    lateinit var d_txtTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        d_txtTitle = findViewById(R.id.d_txtTitle)

        val user = Util().getUser(this)
        user?.let {
            Log.d("itx", it.toString())
            d_txtTitle.setText("Sn. ${it.firstName} ${it.lastName}")
        }

    }

}
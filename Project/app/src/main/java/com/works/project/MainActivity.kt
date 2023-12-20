package com.works.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.works.project.models.Customer

class MainActivity : AppCompatActivity() {

    lateinit var l_txtUsername: EditText
    lateinit var l_txtPassword: EditText
    lateinit var l_btnSend: Button
    lateinit var l_btnSingUp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        l_txtUsername = findViewById(R.id.l_txtUsername)
        l_txtPassword = findViewById(R.id.l_txtPassword)
        l_btnSend = findViewById(R.id.l_btnSend)
        l_btnSingUp = findViewById(R.id.l_btnSingUp)

        // Username Text Change
        l_txtUsername.addTextChangedListener {
            Log.d("username", it.toString())
        }

        // listener push
        l_btnSend.setOnClickListener(sendClick)
        l_btnSingUp.setOnClickListener(singUpClick)

    }


    // Send Click
    val sendClick = View.OnClickListener {
        Log.d("sendClick", "Call")
    }

    // SingUp Click
    val singUpClick = View.OnClickListener {
        // Static Object init
        val customer = Customer(100, "Ali", "Bilmem")
        SingUp.customerItem = customer

        val intent = Intent(this@MainActivity, SingUp::class.java)
        // push data
        intent.putExtra("title", "SingUp Title")
        startActivity(intent)
        // finish()
    }

}
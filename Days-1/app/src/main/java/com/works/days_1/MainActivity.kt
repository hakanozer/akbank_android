package com.works.days_1

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    lateinit var l_txtUsername: EditText
    //lateinit var l_txtPassword: EditText
    lateinit var l_btnLogin: Button
    lateinit var l_btnSingup: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        l_txtUsername = findViewById(R.id.l_txtUsername)
        //l_txtPassword = findViewById(R.id.l_txtPassword)
        l_btnLogin = findViewById(R.id.l_btnLogin)
        l_btnSingup = findViewById(R.id.l_btnSingup)


        l_btnLogin.setOnClickListener {
            // it.setBackgroundColor(Color.RED)
            val username = l_txtUsername.text.toString()
            val password = findViewById<EditText>(R.id.l_txtPassword).text.toString()
            Log.d("username", username)
            Log.d("password", password)
        }

    }




}
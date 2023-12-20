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
import com.works.project.configs.ApiClient
import com.works.project.models.Customer
import com.works.project.models.JWTUser
import com.works.project.models.User
import com.works.project.services.DummyService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var l_txtUsername: EditText
    lateinit var l_txtPassword: EditText
    lateinit var l_btnSend: Button
    lateinit var l_btnSingUp: Button
    lateinit var dummyService: DummyService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        l_txtUsername = findViewById(R.id.l_txtUsername)
        l_txtPassword = findViewById(R.id.l_txtPassword)
        l_btnSend = findViewById(R.id.l_btnSend)
        l_btnSingUp = findViewById(R.id.l_btnSingUp)

        dummyService = ApiClient().getClient().create(DummyService::class.java)

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
        val username = l_txtUsername.text.toString()
        val password = l_txtPassword.text.toString()
        val jwtUser = JWTUser(username, password)
        dummyService.login(jwtUser).enqueue( object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val status = response.code()
                if (status == 200) {
                    val data = response.body()
                    data?.let {
                        Log.d("json", "Login Success ${it}")
                    }
                }else {
                    Log.d("json", "Username or Password Fail!")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("json", "onFailure: ", t )
            }
        })

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
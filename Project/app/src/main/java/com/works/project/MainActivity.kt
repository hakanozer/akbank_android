package com.works.project

import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.works.project.configs.ApiClient
import com.works.project.configs.Util
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

    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: Editor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = Util().getUser(this)
        user?.let {
            //Log.d("user", it.token)
        }

        l_txtUsername = findViewById(R.id.l_txtUsername)
        l_txtPassword = findViewById(R.id.l_txtPassword)
        l_btnSend = findViewById(R.id.l_btnSend)
        l_btnSingUp = findViewById(R.id.l_btnSingUp)

        dummyService = ApiClient().getClient().create(DummyService::class.java)
        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
        editor = sharedPreferences.edit()

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
        if ( username == "" ) {
            Toast.makeText(this, "Username Empty!", Toast.LENGTH_SHORT).show()
            l_txtUsername.requestFocus()
        }else if ( password == "" ) {
            Toast.makeText(this, "Password Empty!", Toast.LENGTH_SHORT).show()
            l_txtPassword.requestFocus()
        }else {
            val jwtUser = JWTUser(username, password)
            dummyService.login(jwtUser).enqueue( object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    val status = response.code()
                    if (status == 200) {
                        val data = response.body()
                        data?.let {
                            val gson = Gson()
                            val data:String = gson.toJson(it)
                            editor.putString("user", data)
                            editor.commit()
                            editor.apply()
                            val intent = Intent(this@MainActivity, Dashboard::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }else {
                        Snackbar.make(l_btnSend, "Username or Password Fail!", Snackbar.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Snackbar.make(l_btnSend, t.toString(), Snackbar.LENGTH_LONG).show()
                }
            })
        }

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
package com.works.project.configs

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.works.project.models.User

class Util {

    fun getUser( context: Context ) : User? {
        val sharedPreferences = context.getSharedPreferences("user", AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val dataString = sharedPreferences.getString("user", "")
        if (dataString == "") {
            return null
        }else {
            try {
                val gson = Gson()
                val user: User = gson.fromJson(dataString, User::class.java)
                return user
            }catch (ex: Exception) {
                editor.remove("user")
                editor.commit()
                return null
            }
        }
    }

}
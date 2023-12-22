package com.works.project

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.works.project.configs.Util

class Dashboard : AppCompatActivity() {

    lateinit var d_txtTitle: TextView
    lateinit var btn1: Button
    lateinit var btn2: Button
    lateinit var btn3: Button
    lateinit var frameLayout: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        d_txtTitle = findViewById(R.id.d_txtTitle)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        frameLayout = findViewById(R.id.frameLayout)


        val user = Util().getUser(this)
        user?.let {
            Log.d("itx", it.toString())
            d_txtTitle.setText("Sn. ${it.firstName} ${it.lastName}")
        }

        val one = One()
        val oneBundle = Bundle()
        oneBundle.putString("key1", "SendData-1")
        one.arguments = oneBundle
        changeFragment(one)

        btn1.setOnClickListener {
            oneBundle.putString("key1", "SendData-1")
            one.arguments = oneBundle
            changeFragment(one)
        }

        btn2.setOnClickListener {
            val two = Two()
            changeFragment(two)
        }

        btn3.setOnClickListener {
            val three = Three()
            changeFragment(three)
        }

    }


    // goto product
    fun gotoProduct( view: View ) {
        val intent = Intent(this@Dashboard, ProductActivity::class.java)
        startActivity(intent)
    }

    // goto likes
    fun gotoLikes( view: View ) {
        val intent = Intent(this@Dashboard, Likes::class.java)
        startActivity(intent)
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Exit")
        builder.setMessage("Are you sure exit!")
        builder.setPositiveButton("Yes") { dialog, wich ->
            super.onBackPressed()
        }
        builder.setNegativeButton("Cancel") { dialog, wich ->

        }
        val alert = builder.create()
        alert.show()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.m_likes) {
            val intent = Intent(this@Dashboard, Likes::class.java)
            startActivity(intent)
        }
        if (item.itemId == R.id.m_logout) {
            val sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            editor.remove("user")
            editor.commit()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        if (item.itemId == R.id.m_map) {
            val intent = Intent(this@Dashboard, MapsActivity::class.java)
            startActivity(intent)
        }
        if (item.itemId == R.id.m_camera) {
            val intent = Intent(this@Dashboard, CameraActivity::class.java)
            startActivity(intent)
        }
        return true
    }

    fun changeFragment ( fragment: Fragment ) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }


}
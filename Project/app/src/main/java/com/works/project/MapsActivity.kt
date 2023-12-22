package com.works.project

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.works.project.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val galata = LatLng(41.0185337, 28.9570762)
        mMap.addMarker(MarkerOptions().position(galata).title("Galata Kulesi"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(galata))

        val istanbulModern = LatLng(41.0252334, 28.975316)
        mMap.addMarker(MarkerOptions().position(istanbulModern).title("İstanbul Moden Kulesi"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(istanbulModern))

        mMap.setOnInfoWindowClickListener {
            if (it.id == "m0") {
                val url = "https://maps.app.goo.gl/XqM3SRhkyQqg7MiH6"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }else if (it.id == "m1") {
                val gsm = "sms:5443332211"
                val intent = Intent(Intent.ACTION_SENDTO, Uri.parse(gsm) )
                intent.putExtra("sms_body", "İstanbul Modern Balosu!")
                startActivity(intent)
            }
        }

        mMap.moveCamera(CameraUpdateFactory.zoomTo(13f))
    }
}
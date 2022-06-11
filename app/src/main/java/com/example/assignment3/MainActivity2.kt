package com.example.assignment3

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity2 : AppCompatActivity() , LocationListener {
    private lateinit var locationManager: LocationManager
    private lateinit var tvGpsLocation: TextView
    private val locationPermissionCode = 2
    private lateinit var progressBar: ProgressBar
    private lateinit var buttonProgress: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        progressBar = findViewById(R.id.progressBar)
        buttonProgress = findViewById(R.id.getLocation)

        var params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams(100,100))
        params.addRule(RelativeLayout.CENTER_IN_PARENT)
        progressBar.layoutParams = params
            title = "TailNode"
            val button: Button = findViewById(R.id.getLocation)
            buttonProgress.setOnClickListener {
                getLocation()
                if (progressBar.visibility == View.INVISIBLE)
                {
                    progressBar.visibility = View.VISIBLE
                }
                else
                {
                    progressBar.visibility = View.INVISIBLE
                }
            }
        }
        private fun getLocation() {
            locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            if ((ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), locationPermissionCode)
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 5f, this)
        }
        override fun onLocationChanged(location: Location) {
            tvGpsLocation = findViewById(R.id.textView)
            tvGpsLocation.text = "Latitude: " + location.latitude + " , Longitude: " + location.longitude
        }
        override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            if (requestCode == locationPermissionCode) {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
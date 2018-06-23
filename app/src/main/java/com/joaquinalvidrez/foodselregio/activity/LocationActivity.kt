package com.joaquinalvidrez.foodselregio.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.places.Places
import com.google.android.gms.location.places.ui.PlacePicker
import com.joaquinalvidrez.foodselregio.R

const val PLACE_PICKER_REQUEST = 1

class LocationActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {
    private lateinit var googleApiClient: GoogleApiClient

    override fun onConnectionFailed(p0: ConnectionResult) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        googleApiClient = GoogleApiClient.Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build()

        startActivityForResult(PlacePicker.IntentBuilder().build(this), PLACE_PICKER_REQUEST)
    }
}

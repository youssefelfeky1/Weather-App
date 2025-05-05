package com.elfeky.weather_app.presentation

import android.content.Context
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat
import android.Manifest
import android.content.pm.PackageManager
import android.location.LocationListener
import android.os.Looper

class LocationUtils(private val context: Context) {
    private val locationManager: LocationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    fun getCurrentLocation(
        onSuccess: (Location) -> Unit,
        onError: (String) -> Unit
    ) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            onError("Location permission not granted")
            return
        }

        try {
            // Try to get last known location first
            val lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                ?: locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

            if (lastKnownLocation != null) {
                onSuccess(lastKnownLocation)
            }


            val locationListener = object : LocationListener {
                override fun onLocationChanged(location: Location) {
                    onSuccess(location)
                    locationManager.removeUpdates(this)
                }
            }

            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                900000L,  // 15 minutes
                100f,    // 100 meters
                locationListener,
                Looper.getMainLooper()
            )

        } catch (e: SecurityException) {
            onError("Location permission denied")
        } catch (e: Exception) {
            onError("Location unavailable: ${e.message}")
        }
    }
}
package com.example.yoann.localisation;

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import static android.content.Context.LOCATION_SERVICE;


public class AppLocationService extends Service implements LocationListener{

    protected LocationManager locationManager; //déclaration des attributs
    Location location;
    private Context mContext;
    private static final long MIN_DISTANCE_FOR_UPDATE = 10; //distance minimale a faire pour actualiser position
    private static final long MIN_TIME_FOR_UPDATE = 1000 * 60 * 2; //temps minimum pour actualiser

    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    boolean canGetLocation = false;


    public AppLocationService(Context context) {
        this.mContext = context; //lien entre mContext et context
        getLocation(); //appel de la méthode getLocation
    }

    public Location getLocation() { //méthode getLocation

        try {// pour traiter les exceptions
            locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE); //on instancie locationmanager pour le manipuler
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);//on s'intéresse au statu du gps
            isNetworkEnabled = locationManager //on s'intéresse au réseau
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {//si ils sont tout deux indisponibles
                                                     //on ne fait rien
            } else { //sinon
                this.canGetLocation = true; //on localise..
                if (isNetworkEnabled) { //..en utilisant le réseau
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_FOR_UPDATE,
                            MIN_DISTANCE_FOR_UPDATE, this);

                    Log.d("Network", "Network");
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                    }
                }

                if (isGPSEnabled) {//..puis en utilisant le GPS
                    if (location == null) {
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                                != PackageManager.PERMISSION_GRANTED &&
                                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                                        != PackageManager.PERMISSION_GRANTED) {
                                //On vérifie les permissions pour localiser l'utilisateur
                        }
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,MIN_TIME_FOR_UPDATE,MIN_DISTANCE_FOR_UPDATE, this);

                        Log.d("GPS Enabled", "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                      }
                    }
                }
            }

        } catch (Exception e) { //capture de l'exception
            e.printStackTrace();
        }

        return location;
    }

    //@Override
    public void onLocationChanged(Location location) {
    }

   // @Override
    public void onProviderDisabled(String provider) {
    }

   // @Override
    public void onProviderEnabled(String provider) {
    }

   // @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

  //  @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
}

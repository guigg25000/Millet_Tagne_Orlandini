package com.example.yoann.localisation;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationAddress {
// localisation inversée pour obtenir addresse à partir de longitude et latitude
    private static final String TAG = "LocationAddress";//déclaration attribut

    public static void getAddressFromLocation(final double latitude, final double longitude,
                                              final Context context, final Handler handler) {//méthode getAddressFromLocation
        Thread thread = new Thread() {
            @Override
            public void run() {
                Geocoder geocoder = new Geocoder(context, Locale.getDefault());//on instancie geocoder pour le manipuler ensuite
                String result = null;
                try { //pour traiter les exceptions
                    List<Address> addressList = geocoder.getFromLocation( //on liste les informations
                            latitude, longitude, 1);
                    if (addressList != null && addressList.size() > 0) { //si la liste n'est pas vide..
                        Address address = addressList.get(0);
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                            sb.append(address.getAddressLine(i)).append("\n");
                        }
                        sb.append(address.getLocality()).append("\n"); //..on localise
                        sb.append(address.getPostalCode()).append("\n");//..on prend le code postal
                        sb.append(address.getCountryName());//..on prend le nom du pays
                        result = sb.toString();
                    }
                } catch (IOException e) {//capture de l'exception
                    Log.e(TAG, "Unable connect to Geocoder", e);//message d'erreur
                } finally {
                    Message message = Message.obtain();
                    message.setTarget(handler);
                    if (result != null) { //si la localisation a fonctionné..
                        message.what = 1;
                        Bundle bundle = new Bundle();//..instancie
                        result = "Latitude: " + latitude + " Longitude: " + longitude +
                                "\n\nAddress:\n" + result; //..on construit une phrase avec les données capturées
                        bundle.putString("address", result);
                        message.setData(bundle);
                    } else {//sinon..
                        message.what = 1;
                        Bundle bundle = new Bundle();
                        result = "Latitude: " + latitude + " Longitude: " + longitude +
                                "\n Unable to get address for this lat-long.";//..message d'erreur pour prévenir utilisateur
                        bundle.putString("address", result);
                        message.setData(bundle);
                    }
                    message.sendToTarget();
                }
            }
        };
        thread.start();
    }




}

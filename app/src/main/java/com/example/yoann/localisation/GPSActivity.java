package com.example.yoann.localisation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GPSActivity extends AppCompatActivity {

        public static final String ENDPOINT_URL ="http://10.188.141.166:3306"; // Indique addresse du serveur pour la BDD

         //declaration des attributs
        Button btnLocation; //Boutons localisation et adresse qui n'existeront plus dans l'app finale
        Button btnAddress;
        TextView textAddress; //texte qui affiche la localisation et qui n'existera plus non plus dans l'app finale

        AppLocationService appLocationService;

        @Override
        protected void onCreate(Bundle savedInstanceState) { // lors de la création de l'activité
            super.onCreate(savedInstanceState); //appel de la superclasse
            setContentView(R.layout.gps_main); //lien entre l'activité et le design activity_main.xml
            textAddress = (TextView) findViewById(R.id.textAddress); //lien avec le texte du layout
            appLocationService = new AppLocationService(
                    GPSActivity.this);

            btnLocation = (Button) findViewById(R.id.btnLocation);//lien avec boutton du layout
            btnLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) { //lors du click sur le bouton..

                    appLocationService = new  AppLocationService(GPSActivity.this); //.. on instancie appLocationService pour le manipuler
                    Location gpsLocation = appLocationService.getLocation(); //.. on essaye d'obtenir la localisation


                    if (gpsLocation != null) {          //si ca a marché on enregistre les valeurs
                        double latitude = gpsLocation.getLatitude(); //on enregistre la valeur de la latitude
                        double longitude = gpsLocation.getLongitude(); // on enregistre la valeur de la longitude
                        String result = "Latitude: " + gpsLocation.getLatitude() +
                                " Longitude: " + gpsLocation.getLongitude(); //on les affiche dans une phrase
                        textAddress.setText(result);  //on affiche cette phrase grace au layout
                    } else {        //Sinon
                        showSettingsAlert(); //appel la méthode showSettingAlert
                    }
                }
            });

            btnAddress = (Button) findViewById(R.id.btnAddress);//lien boutton avec layout
            btnAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {//lors du click sur le bouton..

                    appLocationService = new  AppLocationService(GPSActivity.this); //.. on instancie appLocationService
                    Location location = appLocationService.getLocation(); //.. on essaye d'obtenir la localisation

                    if (location != null) {     //si ça a marché
                        double latitude = location.getLatitude();   //on enregistre la latitude
                        double longitude = location.getLongitude(); //on enregistre la longitude
                        LocationAddress locationAddress = new LocationAddress(); //on instancie LocationAddress pour le manipuler
                        locationAddress.getAddressFromLocation(latitude, longitude,//on fait une localisation inversée grace aux données précédentes
                                getApplicationContext(), new GeocoderHandler());
                    } else { //Sinon
                        showSettingsAlert(); //appel la méthode showSettingAlert
                    }

                }
            });

        }

        public void showSettingsAlert() { //si on a pas réussi a obtenir des valeurs lors du clique sur les boutons
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                    GPSActivity.this); //on instancie pour manipuler AlertDialog
            alertDialog.setTitle("SETTINGS"); //Titre de l'alerte
            alertDialog.setMessage("You must allow the application to locate you if you want to go forward"); //message d'erreur informant l'utilisateur
            alertDialog.setPositiveButton("Settings", //Si on appuie sur le bouton setting
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent( //on instancie intent pour le manipuler
                                    Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            GPSActivity.this.startActivity(intent);
                        }
                    });
            alertDialog.setNegativeButton("Cancel",//Si on appuie sur le bouton cancel
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });//on annule
            alertDialog.show();
        }

        private class GeocoderHandler extends Handler { //localisation inversée
            @Override
            public void handleMessage(Message message) {
                String locationAddress;
                switch (message.what) {
                    case 1:
                        Bundle bundle = message.getData();
                        locationAddress = bundle.getString("address");
                        break;
                    default:
                        locationAddress = null;
                }
                textAddress.setText(locationAddress); // on affiche adresse
            }
        }

        //RETROFIT    cette partie n'est pas terminée
/**
    Retrofit retrofit = new Retrofit.Builder().baseUrl(ENDPOINT_URL).
            addConverterFactory(GsonConverterFactory.create()).
            build();

        GetInfo getInfo = retrofit.create(GetInfo.class);
        Call<Test> call = getInfo.all();
        Response<Test> response = null;

    {
        try {
            response = call.execute();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Test test = response.body();
    }


    public void insertData(int idUser,int idPicture,String login,String password)
    {
        // call Inter face method
        Call<Todo> call=GetInfo.serverCall(idUser,idPicture,login,password);
        call.enqueue(new Callback<Todo>() {

    }
**/


}


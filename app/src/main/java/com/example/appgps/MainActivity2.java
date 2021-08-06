package com.example.appgps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {

    Button btnSalir, iniciarGPS;
    TextView longitud, latitud, altitud, direccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnSalir = (Button)findViewById(R.id.btnSalir);
        iniciarGPS = (Button)findViewById(R.id.btnGPS);
        longitud = (TextView)findViewById(R.id.longitud);
        altitud = (TextView)findViewById(R.id.altitud);
        latitud = (TextView)findViewById(R.id.latitud);
        direccion = (TextView)findViewById(R.id.direccion);

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        iniciarGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationManager locationManager = (LocationManager) MainActivity2.this.getSystemService(Context.LOCATION_SERVICE);

                LocationListener locationListener = new LocationListener() {
                    @Override
                    public void onLocationChanged(@NonNull Location location) {
                        longitud.setText(String.valueOf(location.getLongitude()));
                        latitud.setText(String.valueOf(location.getLatitude()));
                        altitud.setText(String.valueOf(location.getAltitude()));
                        Toast.makeText(MainActivity2.this, "Mostrando coordenadas actuales.", Toast.LENGTH_SHORT).show();

                        if (location.getLatitude() != 0.0 && location.getLongitude() != 0.0) {
                            try {
                                Geocoder geocoder = new Geocoder(MainActivity2.this, Locale.getDefault());
                                List<Address> list = geocoder.getFromLocation(
                                        location.getLatitude(), location.getLongitude(), 1);
                                if (!list.isEmpty()) {
                                    Address DirCalle = list.get(0);
                                    direccion.setText(DirCalle.getAddressLine(0));
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    public  void onStatusChanged(String provider, int status, Bundle extras){}
                    public void onProviderEnabled(String provider){}
                    public void onProviderDisabled(String provider){}
                };

                int permiso = ContextCompat.checkSelfPermission(MainActivity2.this, Manifest.permission.ACCESS_FINE_LOCATION);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
            }
        });

        int permiso = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permiso == PackageManager.PERMISSION_DENIED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){

            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }
}
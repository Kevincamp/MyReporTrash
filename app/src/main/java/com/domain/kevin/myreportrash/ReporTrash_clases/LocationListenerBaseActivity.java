package com.domain.kevin.myreportrash.ReporTrash_clases;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by rbalda on 12/01/16.
 */
public abstract  class LocationListenerBaseActivity extends AppCompatActivity implements LocationListener {
    protected LocationManager mLocationManager;
    protected double lat ;
    protected double longi ;

    public void init_location_manager(){
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        //criteria code using for non gps
        Criteria criteria = new Criteria();
        String provider= null;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        try {
            criteria.setAccuracy(Criteria.ACCURACY_FINE);
            provider = mLocationManager.getBestProvider(criteria, true);
            this.lat = mLocationManager.getLastKnownLocation(provider).getLatitude();
            this.longi = mLocationManager.getLastKnownLocation(provider).getLongitude();
        }catch (Exception ex){

            provider = LocationManager.NETWORK_PROVIDER;
            this.lat = mLocationManager.getLastKnownLocation(provider).getLatitude();
            this.longi = mLocationManager.getLastKnownLocation(provider).getLongitude();
        }

        mLocationManager.requestLocationUpdates(provider, 0, 0, this);

    }
}

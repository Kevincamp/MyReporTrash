package com.domain.kevin.myreportrash;

import android.Manifest;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MisReportes_Fragment extends Fragment {

    GoogleMap googleMap;
    MapView mapView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.misreportes_layout, container, false);

        getActivity().setTitle("Mis Reportes");

        mapView = (MapView) view.findViewById(R.id.mi_mapa);
        mapView.onCreate(savedInstanceState);;

        googleMap = mapView.getMap();
        googleMap.setMapType(googleMap.MAP_TYPE_NORMAL);
        googleMap.getMyLocation();

        googleMap.addMarker(new MarkerOptions().position(new LatLng(0.0,0.0)));

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }
}

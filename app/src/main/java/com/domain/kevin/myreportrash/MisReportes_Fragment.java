package com.domain.kevin.myreportrash;

import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MisReportes_Fragment extends android.support.v4.app.Fragment {

    GoogleMap googleMap;
    SupportMapFragment fragment;
    Location location;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.misreportes_layout, container, false);

        getActivity().setTitle("Mis Reportes");



        //googleMap.addMarker(new MarkerOptions().position(new LatLng(0.0,0.0)));

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentManager fm = getChildFragmentManager();
        fragment = (SupportMapFragment) fm.findFragmentById(R.id.relativeLayout);
        if(fragment == null){

            fragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.relativeLayout,fragment)
            .commit();

        }

        googleMap = fragment.getMap();
        //googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        //googleMap.addMarker(new MarkerOptions().position(new LatLng(0.0,00))
        //.title("Basura 1"));


    }



    @Override
        public void onResume() {
            super.onResume();
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
        }

        @Override
        public void onPause() {
            super.onPause();
        }
}

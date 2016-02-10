package com.domain.kevin.myreportrash;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.SensorManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.domain.kevin.myreportrash.ReporTrash_clases.Basura;
import com.domain.kevin.myreportrash.ReporTrash_clases.Usuario;
import com.domain.kevin.myreportrash.ReporTrash_db.DBHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reportar_Fragment extends Fragment implements LocationListener{

    Intent i;
    private ImageButton btn_capturar;
    private Button btn_reportar;
    private TextView txt_direccion, txt_detalle;

    private Usuario usuario;
    String username;
    private ImageView imageView;
    String foto = "";
    private Bitmap bmp;
    static Uri capturedImageUri = null;
    public static final int CAMERA_REQUEST = 0;
    public static final int TAKE_PICTURE = 1;
    Location location;
    LocationManager mLocationManager;
    double lat ;
    double longi ;
    private SensorManager mSensorManager;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( requestCode == TAKE_PICTURE){
            try{
                Bitmap bmp = MediaStore.Images.Media.getBitmap(
                        getActivity().getContentResolver(),
                        Uri.parse(foto));
                imageView = (ImageView)getActivity().findViewById(R.id.imagen);
                imageView.setImageBitmap(bmp);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reportar_layout,container,false);
        final DBHandler db = new DBHandler(getActivity().getApplicationContext());
        getActivity().setTitle("Reportar Basura");

        imageView = (ImageView)view.findViewById(R.id.imagen);
        btn_capturar = (ImageButton)view.findViewById(R.id.btn_capturar);
        btn_reportar = (Button)view.findViewById(R.id.btn_reportar);
        txt_direccion = (TextView)view.findViewById(R.id.txt_direccion);
        txt_detalle = (TextView)view.findViewById(R.id.txt_detalle);

        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        this.init_location_manager();
        init_location();

        Bundle bundle = this.getArguments();
        if(bundle != null){
            username = bundle.getString("username", "DEFAULT_USER_NAME");
        }
        usuario = db.getUsuario(username);

        btn_capturar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        btn_reportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (verificarCampos()) {
                    Toast.makeText(getActivity().getApplicationContext(), "Faltan Campos por Ingresar.", Toast.LENGTH_LONG).show();

                } else {
                    db.addBasura(new Basura(txt_direccion.getText().toString(),
                            txt_detalle.getText().toString(),
                            foto,
                            location.getLatitude(),
                            location.getLongitude(),
                            usuario.getId()));

                    Toast.makeText(getActivity().getApplicationContext(), "Basura Reportada.", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent
                .resolveActivity(getActivity().getPackageManager()) != null) {

            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }

            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, TAKE_PICTURE);
            }
        }
    }

    private File createImageFile() throws IOException {

        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss")
                .format(new Date());
        String imageFileName = "HuecApp" + "_" + timeStamp + "_";
        File storageDir = Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, /* prefix */
                ".jpg", /* suffix */
                storageDir /* directory */
        );
        // Save a file: path for use with ACTION_VIEW intents
        foto = "file:" + image.getAbsolutePath();
        return image;
    }

    private boolean verificarCampos(){
        boolean res = false;
        if(txt_direccion.getText().toString().contentEquals("")){
            res = true;
            return res;
        }
        if(txt_detalle.getText().toString().contentEquals("")){
            res = true;
            return res;
        }
        /*if(foto == null){
            res = false;
            return res;
        }*/
        return res;
    }

    public void init_location_manager(){
        mLocationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        //criteria code using for non gps
        Criteria criteria = new Criteria();
        String provider= null;

        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
            lat = mLocationManager.getLastKnownLocation(provider).getLatitude();
            longi = mLocationManager.getLastKnownLocation(provider).getLongitude();
        }catch (Exception ex){

            provider = LocationManager.NETWORK_PROVIDER;
            lat = mLocationManager.getLastKnownLocation(provider).getLatitude();
            longi = mLocationManager.getLastKnownLocation(provider).getLongitude();
        }


        mLocationManager.requestLocationUpdates(provider, 0, 0,this);

    }

    @Override
    public void onLocationChanged(Location location) {
        this.location=location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void init_location(){
        this.location = new Location("");
        this.location.setLatitude(this.lat);
        this.location.setLongitude(this.longi);
    }
}

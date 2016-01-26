package com.domain.kevin.myreportrash;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.domain.kevin.myreportrash.ReporTrash_Servicios.ServicioGPS;
import com.domain.kevin.myreportrash.ReporTrash_clases.Basura;
import com.domain.kevin.myreportrash.ReporTrash_db.DBHandler;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class Reportar_Fragment extends Fragment {

    Intent i;
    private ImageButton btn_capturar;
    private Button btn_reportar;
    private TextView txt_direccion, txt_detalle;


    private ImageView imageView;
    File file;
    private Bitmap bmp;
    static Uri capturedImageUri = null;
    public static final int CAMERA_REQUEST = 0;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            Bundle ext = data.getExtras();
            bmp = (Bitmap)ext.get("data");
            imageView.setImageBitmap(bmp);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reportar_layout,container,false);
        getActivity().setTitle("Reportar Basura");
        imageView = (ImageView)view.findViewById(R.id.imageView);
        btn_capturar = (ImageButton)view.findViewById(R.id.btn_capturar);
        btn_reportar = (Button)view.findViewById(R.id.btn_reportar);
        txt_direccion = (TextView)view.findViewById(R.id.txt_direccion);
        txt_detalle = (TextView)view.findViewById(R.id.txt_detalle);

        final DBHandler db = new DBHandler(getActivity().getApplicationContext());

        btn_capturar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Calendar cal = Calendar.getInstance();
                file = new File(Environment.getExternalStorageDirectory(), (cal.getTimeInMillis() + ".jpg"));
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else {
                    file.delete();
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                capturedImageUri = Uri.fromFile(file);*/
                i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                //i.putExtra(MediaStore.EXTRA_OUTPUT, capturedImageUri);
                startActivityForResult(i, CAMERA_REQUEST);
            }
        });

        btn_reportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (verificarCampos()) {
                    Toast.makeText(getActivity().getApplicationContext(), "Faltan Campos por Ingresar.", Toast.LENGTH_LONG).show();

                } else {
                    ServicioGPS servicioGPS = new ServicioGPS(getActivity().getApplicationContext());

                    db.addBasura(new Basura(txt_direccion.getText().toString(),
                            txt_detalle.getText().toString(),
                            file.getPath(),
                            (double) servicioGPS.getLatitud(),
                            (double) servicioGPS.getLongitud(),
                            1));

                    Toast.makeText(getActivity().getApplicationContext(), "Basura Reportada.", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

    private boolean verificarCampos(){
        boolean res = true;
        if(txt_direccion.getText().equals("")){
            res = false;
            return res;
        }
        if(txt_detalle.getText().equals("")){
            res = false;
            return res;
        }
        if(imageView.getResources() == null){
            res = false;
            return res;
        }
        return res;
    }


}

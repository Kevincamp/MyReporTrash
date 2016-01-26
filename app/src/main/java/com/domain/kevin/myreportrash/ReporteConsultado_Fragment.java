package com.domain.kevin.myreportrash;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by kevin on 1/24/16.
 */
public class ReporteConsultado_Fragment extends Fragment {
    private TextView txt_direccion;
    private TextView txt_detalle;
    ImageView imagen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reporteconsultado_layout,container,false);
        getActivity().setTitle("Reporte de Basura");

        txt_detalle = (TextView)view.findViewById(R.id.txt_detalle);
        txt_direccion = (TextView)view.findViewById(R.id.txt_direccion);
        imagen = (ImageView)view.findViewById(R.id.imagenConsultada);





        return view;
    }
}

package com.domain.kevin.myreportrash;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.domain.kevin.myreportrash.ReporTrash_clases.HuellaEcologica;

import java.text.DecimalFormat;

/**
 * Created by kevin on 2/8/16.
 */
public class HuellaEcologica_Fragment extends android.support.v4.app.Fragment {
    private TextView comida_resultado;
    private TextView movilidad_resultado;
    private TextView agua_resultado;
    private TextView electricidad_resultado;
    private TextView resultado_value;
    private HuellaEcologica huellaEcologica;

    Double comida,movilidad,agua,electricidad, total;

    private static DecimalFormat REAL_FORMATTER = new DecimalFormat("0.###");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.resultado_layout,container,false);

        getActivity().setTitle("Calculo de Huella Ecologica.");

        Bundle bundle = this.getArguments();
        if(bundle != null){
            huellaEcologica = bundle.getParcelable("HuellaEcologica");
        }
        resultado_value = (TextView)view.findViewById(R.id.resultado_value);

        comida = huellaEcologica.getComida();
        movilidad = huellaEcologica.getMovilidad();
        agua = huellaEcologica.getAgua();
        electricidad = huellaEcologica.getElectricidad();
        total = comida + movilidad + agua + electricidad;


        resultado_value.setText(REAL_FORMATTER.format(total));

        return view;
    }
}

package com.domain.kevin.myreportrash;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.domain.kevin.myreportrash.ReporTrash_clases.HuellaEcologica;

/**
 * Created by kevin on 2/8/16.
 */
public class HuellaEcologica_Fragment extends android.support.v4.app.Fragment {
    private TextView comida_resultado;
    private TextView movilidad_resultado;
    private TextView agua_resultado;
    private TextView electricidad_resultado;
    private HuellaEcologica huellaEcologica;

    Integer comida,movilidad,agua,electricidad;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.huellaecologica_layout,container,false);

        Bundle bundle = this.getArguments();
        if(bundle != null){
            huellaEcologica = bundle.getParcelable("HuellaEcologica");
        }

        comida_resultado = (TextView)view.findViewById(R.id.comida_resultado);
        movilidad_resultado = (TextView)view.findViewById(R.id.movilidad_resultado);
        agua_resultado = (TextView)view.findViewById(R.id.agua_resultado);
        electricidad_resultado = (TextView)view.findViewById(R.id.electricidad_resultado);

        comida = huellaEcologica.getComida();
        movilidad = huellaEcologica.getMovilidad();
        agua = huellaEcologica.getAgua();
        electricidad = huellaEcologica.getElectricidad();

        comida_resultado.setText(comida.toString());
        movilidad_resultado.setText(movilidad.toString());
        agua_resultado.setText(agua.toString());
        electricidad_resultado.setText(electricidad.toString());

        return view;
    }
}

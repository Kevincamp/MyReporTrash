package com.domain.kevin.myreportrash;


import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.domain.kevin.myreportrash.ReporTrash_clases.HuellaEcologica;

public class Calcular_Fragment extends Fragment {
    private Button btn_resultado;
    private TextView txt_comida, txt_movilidad, txt_agua, txt_electricidad;
    private int comida_value, movilidad_value, agua_value, electricidad_value;
    public HuellaEcologica huellaEcologica;
    private FragmentTransaction fragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calcular_layout,container,false);
        getActivity().setTitle("Calcular Huella Ecologica");

        txt_comida = (TextView)view.findViewById(R.id.txt_comida);
        txt_movilidad = (TextView)view.findViewById(R.id.txt_movilidad);
        txt_agua = (TextView)view.findViewById(R.id.txt_agua);
        txt_electricidad = (TextView)view.findViewById(R.id.txt_electricidad);
        btn_resultado = (Button)view.findViewById(R.id.btn_calculaResultado);


        btn_resultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comida_value = Integer.parseInt(txt_comida.getText().toString());
                movilidad_value = Integer.parseInt(txt_movilidad.getText().toString());
                agua_value = Integer.parseInt(txt_agua.getText().toString());
                electricidad_value = Integer.parseInt(txt_electricidad.getText().toString());

                comida_value = (int)(comida_value * 58.5);
                movilidad_value = (movilidad_value * 4800);
                agua_value = (agua_value * 59760);
                electricidad_value = (electricidad_value * 1219);

                huellaEcologica = new HuellaEcologica(comida_value,movilidad_value,agua_value,electricidad_value);

                HuellaEcologica_Fragment huellaEcologica_fragment = new HuellaEcologica_Fragment();

                Bundle bundle = new Bundle();
                bundle.putParcelable("HuellaEcologica", huellaEcologica);
                huellaEcologica_fragment.setArguments(bundle);

                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_frame,huellaEcologica_fragment)
                        .addToBackStack("home").commit();


            }
        });


        return view;
    }
}

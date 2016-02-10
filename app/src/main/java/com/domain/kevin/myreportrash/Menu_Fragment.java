package com.domain.kevin.myreportrash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.domain.kevin.myreportrash.ReporTrash_clases.Usuario;
import com.domain.kevin.myreportrash.ReporTrash_db.DBHandler;

public class Menu_Fragment extends Fragment {

    private Button btn_info;
    private Button btn_reportar;
    private Button btn_calcular;
    private Button btn_misreportes;
    String username;
    Usuario usuario;
    Bundle bundle;

    private FragmentTransaction fragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_layout,container,false);
        final DBHandler db = new DBHandler(this.getContext());

        bundle = this.getArguments();
        if(bundle != null){
            username = bundle.getString("username", "Administrador");
            usuario = db.getUsuario(username);

        }

        getActivity().setTitle("ReporTrasH");

        btn_reportar = (Button)view.findViewById(R.id.btn_reportar);
        btn_reportar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Bundle bundleReport = new Bundle();
                bundleReport.putString("username", usuario.getUsername());

                Reportar_Fragment reportar_fragment = new Reportar_Fragment();
                reportar_fragment.setArguments(bundleReport);

                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_frame,reportar_fragment)
                        .addToBackStack("home").commit();
            }
        });

        btn_info = (Button)view.findViewById(R.id.btn_info);
        btn_info.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Info_Fragment info_fragment = new Info_Fragment();
                        fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.content_frame,info_fragment)
                                .addToBackStack("home").commit();
                    }
                }
        );

        btn_calcular = (Button)view.findViewById(R.id.btn_calculate);
        btn_calcular.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Calcular_Fragment calcular_fragment = new Calcular_Fragment();
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_frame,calcular_fragment)
                .addToBackStack("home").commit();

            }
        });

        btn_misreportes = (Button)view.findViewById(R.id.btn_misreportes);
        btn_misreportes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Bundle bundleMisReportes = new Bundle();
                bundleMisReportes.putString("username", usuario.getUsername());

                MisReportes2_Fragment misReportes2_fragment = new MisReportes2_Fragment();
                misReportes2_fragment.setArguments(bundleMisReportes);

                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.content_frame,misReportes2_fragment)
                        .addToBackStack("home").commit();
            }
        });


        return view;

    }
}

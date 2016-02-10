package com.domain.kevin.myreportrash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.domain.kevin.myreportrash.ReporTrash_clases.Basura;
import com.domain.kevin.myreportrash.ReporTrash_clases.Usuario;
import com.domain.kevin.myreportrash.ReporTrash_db.DBHandler;

import java.util.ArrayList;
import java.util.List;

public class MisReportes2_Fragment extends Fragment {
    private ArrayList<Basura> basuraList;
    private String username;
    private Usuario usuario;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final DBHandler db = new DBHandler(getContext());

        Bundle bundle = this.getArguments();
        if(bundle != null){
            username = bundle.getString("username", "DEFAULT_USER_NAME");
        }

        usuario = db.getUsuario(username);
        basuraList = db.selectAllBasura(usuario.getId());

        View view = inflater.inflate(R.layout.misreportes2_layout,container,false);
        RecyclerView rv = (RecyclerView)view.findViewById(R.id.misReportesrv);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);

        final BasuraAdapter ba =new BasuraAdapter(getActivity(),(List)basuraList);
        rv.setAdapter(ba);

        return view;
    }


}

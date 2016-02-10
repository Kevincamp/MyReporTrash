package com.domain.kevin.myreportrash;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.domain.kevin.myreportrash.ReporTrash_clases.Basura;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.List;

public class BasuraAdapter extends RecyclerView.Adapter<BasuraAdapter.BasuraViewHolder> {
    private List<Basura> basuraList;
    private Context context;
    private Basura b;

    public BasuraAdapter(Context context, List<Basura> basuraList){
        this.context = context;
        this.basuraList = basuraList;
    }

    public Basura getBasura(int posicion){
        return basuraList.get(posicion);
    }

    @Override
    public BasuraViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.basura_item_layout, viewGroup, false);
        return new BasuraViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BasuraViewHolder basuraViewHolder, int i) {
        b = basuraList.get(i);
        basuraViewHolder.vDireccion.setText(b.getDireccion());
        basuraViewHolder.vDescripcion.setText(b.getDetalle());
        //ImageLoader imageLoader = ImageLoader.getInstance();
        /*DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisk(true).resetViewBeforeLoading(true)
                .build();
        imageLoader.displayImage(b.getImagen(), basuraViewHolder.vFotoBasura, options);*/
    }

    @Override
    public int getItemCount() {
        return basuraList.size();
    }

    public class BasuraViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected TextView vDireccion;
        protected TextView vDescripcion;
        //protected ImageView vFotoBasura;

        public BasuraViewHolder(View v) {
            super(v);
            vDireccion =  (TextView) v.findViewById(R.id.direccion_basura_item);
            vDescripcion = (TextView)  v.findViewById(R.id.descrp_basura_item);
            //vFotoBasura = (ImageView) v.findViewById(R.id.imagen_basura_item);
        }


        @Override
        public void onClick(View v) {

        }
    }
}

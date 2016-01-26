package com.domain.kevin.myreportrash.ReporTrash_clases;

import android.os.Parcel;
import android.os.Parcelable;


public class Basura implements Parcelable{
    protected int id;
    protected String direccion;
    protected String detalle;
    protected String imagen;
    protected Double latitud;
    protected Double longuitud;
    protected String fecha;
    protected int usuario_id;

    public Basura (){

    }

    public Basura(int basuraid, String direccion, String detalle, String imagen, Double latitud, Double longuitud,int usuario_id){
        this.id = basuraid;
        this.usuario_id = usuario_id;
        this.direccion = direccion;
        this.detalle = detalle;
        this.imagen = imagen;
        this.latitud = latitud;
        this.longuitud = longuitud;
    }
    public Basura(String direccion, String detalle, String imagen, Double latitud, Double longuitud,int usuario_id){
        this.usuario_id = usuario_id;
        this.direccion = direccion;
        this.detalle = detalle;
        this.imagen = imagen;
        this.latitud = latitud;
        this.longuitud = longuitud;
    }

    public void setUsuario_id(int usuario_id){this.usuario_id = usuario_id;}
    public int getUsuario_id(){return usuario_id;}

    public void setDireccion(String direccion){this.direccion = direccion;}
    public String getDireccion(){return direccion;}

    public void setDetalle(String detalle){this.detalle = detalle;}
    public String getDetalle(){return detalle;}

    public void setImagen(String imagen){this.imagen = imagen;}
    public String getImagen(){return imagen;}

    public void setLatitud(Double latitud){this.latitud = latitud;}
    public Double getLatitud(){return latitud;}

    public void setLonguitud(Double longuitud){this.longuitud = longuitud;}
    public Double getLonguitud(){return longuitud;}

    public String getFecha(){return fecha;}

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.direccion);
        dest.writeString(this.detalle);
        dest.writeString(this.imagen);
        dest.writeDouble(this.latitud);
        dest.writeDouble(this.longuitud);
        dest.writeInt(this.usuario_id);
    }

    protected Basura(Parcel in){
        this.id = in.readInt();
        this.direccion = in.readString();
        this.detalle = in.readString();
        this.imagen = in.readString();
        this.latitud = in.readDouble();
        this.longuitud = in.readDouble();
        this.usuario_id = in.readInt();
    }

    public static final Creator<Basura> CREATOR = new Creator<Basura>() {
        public Basura createFromParcel(Parcel source) {
            return new Basura(source);
        }

        public Basura[] newArray(int size) {
            return new Basura[size];
        }
    };
}

package com.domain.kevin.myreportrash.ReporTrash_clases;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kevin on 2/8/16.
 */
public class HuellaEcologica implements Parcelable{
    protected Double comida;
    protected Double movilidad;
    protected Double agua;
    protected Double electricidad;

    public HuellaEcologica(){

    }

    public HuellaEcologica(Double comida, Double movilidad, Double agua, Double electricidad){
        this.comida = comida;
        this.movilidad = movilidad;
        this.agua = agua;
        this.electricidad = electricidad;
    }

    public Double getComida(){
        return comida;
    }
    public void setComida(Double comida){
        this.comida = comida;
    }

    public Double getMovilidad(){
        return movilidad;
    }
    public void setMovilidad(Double movilidad){
        this.movilidad = movilidad;
    }

    public Double getAgua(){
        return agua;
    }
    public void setAgua(Double agua){
        this.agua = agua;
    }

    public Double getElectricidad(){
        return electricidad;
    }
    public void setElectricidad(Double electricidad){
        this.electricidad = electricidad;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.comida);
        dest.writeDouble(this.movilidad);
        dest.writeDouble(this.agua);
        dest.writeDouble(this.electricidad);

    }

    protected HuellaEcologica(Parcel in){
        this.comida = in.readDouble();
        this.movilidad = in.readDouble();
        this.agua = in.readDouble();
        this.electricidad = in.readDouble();
    }
    public static final Creator<HuellaEcologica> CREATOR = new Creator<HuellaEcologica>() {
        @Override
        public HuellaEcologica createFromParcel(Parcel source) {
            return new HuellaEcologica(source);
        }

        @Override
        public HuellaEcologica[] newArray(int size) {
            return new HuellaEcologica[size];
        }
    };
}

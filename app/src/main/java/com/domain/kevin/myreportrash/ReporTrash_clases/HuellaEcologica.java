package com.domain.kevin.myreportrash.ReporTrash_clases;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kevin on 2/8/16.
 */
public class HuellaEcologica implements Parcelable{
    protected int comida;
    protected int movilidad;
    protected int agua;
    protected int electricidad;

    public HuellaEcologica(){

    }

    public HuellaEcologica(int comida, int movilidad, int agua, int electricidad){
        this.comida = comida;
        this.movilidad = movilidad;
        this.agua = agua;
        this.electricidad = electricidad;
    }

    public int getComida(){
        return comida;
    }
    public void setComida(int comida){
        this.comida = comida;
    }

    public int getMovilidad(){
        return movilidad;
    }
    public void setMovilidad(int movilidad){
        this.movilidad = movilidad;
    }

    public int getAgua(){
        return agua;
    }
    public void setAgua(int agua){
        this.agua = agua;
    }

    public int getElectricidad(){
        return electricidad;
    }
    public void setElectricidad(int electricidad){
        this.electricidad = electricidad;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.comida);
        dest.writeInt(this.movilidad);
        dest.writeInt(this.agua);
        dest.writeInt(this.electricidad);

    }

    protected HuellaEcologica(Parcel in){
        this.comida = in.readInt();
        this.movilidad = in.readInt();
        this.agua = in.readInt();
        this.electricidad = in.readInt();
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

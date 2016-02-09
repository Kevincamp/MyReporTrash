package com.domain.kevin.myreportrash.ReporTrash_clases;
import android.os.Parcel;
import android.os.Parcelable;

public class Usuario implements Parcelable
{
    protected int id;
    public String nombre;
    public String apellido;
    //protected String imagen;
    protected String username;
    protected String password;
    protected String email;
    public Usuario(){

    }

    public Usuario(int id, String username, String password, String nombre, String apellido, String email){
        this.id = id;
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    public Usuario(String username, String password, String nombre, String apellido, String email){
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }
    public int getId(){return id;}
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return username;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getNombre(){
        return nombre;
    }

    public void setApellido(String apellido){
        this.apellido = apellido;
    }
    public String getApellido(){
        return apellido;
    }

    /*public void setImagen(String imagen){
        this.imagen = imagen;
    }
    public String getImagen(){
        return imagen;
    }*/

    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nombre);
        dest.writeString(this.apellido);
        //dest.writeString(this.imagen);
        dest.writeString(this.username);
        dest.writeString(this.password);
        dest.writeString(this.email);
    }

    protected Usuario(Parcel in) {
        this.id = in.readInt();
        this.nombre = in.readString();
        this.apellido = in.readString();
        //this.imagen = in.readString();
        this.username = in.readString();
        this.password = in.readString();
        this.email = in.readString();
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        public Usuario createFromParcel(Parcel source) {
            return new Usuario(source);
        }

        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };
}

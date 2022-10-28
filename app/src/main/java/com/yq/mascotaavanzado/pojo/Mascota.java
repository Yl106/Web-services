package com.yq.mascotaavanzado.pojo;

public class Mascota {
    private int id, foto;
    private String nombre;
    private String identificacion;
    private String raza;
    private int raiting = 0;

    public Mascota() {
    }

    public Mascota(int id, int foto, int raiting) {
        this.id = id;
        this.foto = foto;
        this.raiting = raiting;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getContador() {
        return raiting;
    }

    public void setContador(int contador) {
        this.raiting = contador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getRaiting() {
        return raiting;
    }

    public void setRaiting(int raiting) {
        this.raiting = raiting;
    }
}

package com.example.lab2;

import java.io.Serializable;

public class Switch implements Serializable {

    private String marca;
    private String modelo;
    private String cantidad_puertos;
    private String tipo;
    private String estado;

    public Switch(String marca, String modelo, String cantidad_puertos, String tipo ,String estado) {
        this.marca = marca;
        this.modelo = modelo;
        this.cantidad_puertos = cantidad_puertos;
        this.tipo = tipo;
        this.estado = estado;
    }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public String getCantidad_puertosd() { return cantidad_puertos; }
    public void setCantidad_puertos(String cantidad_puertos) { this.cantidad_puertos = cantidad_puertos; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}

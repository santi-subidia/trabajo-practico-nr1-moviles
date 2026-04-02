package com.subitech.trabajopractico_nro1.model;

import java.io.Serializable;
import java.util.Objects;

public class Moneda implements Serializable {
    private String nombre;
    private double valorIntercambio;

    public Moneda(String nombre, double valorIntercambio, double valorConvertir) {
        this.nombre = nombre;
        this.valorIntercambio = valorIntercambio;
    }

    public Moneda(String nombre, double valorIntercambio) {
        this.nombre = nombre;
        this.valorIntercambio = valorIntercambio;
    }

    public Moneda(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getValorIntercambio() {
        return valorIntercambio;
    }

    public void setValorIntercambio(double valorIntercambio) {
        this.valorIntercambio = valorIntercambio;
    }

    public double getConversion(double valor){
        return valor * valorIntercambio;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Moneda moneda = (Moneda) o;
        return Objects.equals(nombre, moneda.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

}

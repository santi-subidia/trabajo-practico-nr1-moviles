package com.subitech.trabajopractico_nro1.model;

import java.io.Serializable;
import java.util.Map;

public class VistaMainActivity implements Serializable {
    private String valorPrimerMoneda;
    private String valorSegundaMoneda;
    private Moneda moneda;

    public VistaMainActivity() {
        valorPrimerMoneda = "";
        valorSegundaMoneda = "";
    }

    public String getValorPrimerMoneda() {
        return valorPrimerMoneda;
    }

    public void setValorPrimerMoneda(String valorPrimerMoneda) {
        this.valorPrimerMoneda = valorPrimerMoneda;
    }

    public String getValorSegundaMoneda() {
        return valorSegundaMoneda;
    }

    public void setValorSegundaMoneda(String valorSegundaMoneda) {
        this.valorSegundaMoneda = valorSegundaMoneda;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }
}

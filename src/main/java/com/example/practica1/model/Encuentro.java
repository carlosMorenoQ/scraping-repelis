package com.example.practica1.model;

import java.util.HashMap;
import java.util.Map;

public class Encuentro {

    private String local;
    private String visitante;

    public Encuentro() {
    }

    public Encuentro(String local, String visitante) {
        this.local = local;
        this.visitante = visitante;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getVisitante() {
        return visitante;
    }

    public void setVisitante(String visitante) {
        this.visitante = visitante;
    }

    @Override
    public String toString() {
        return "Encuentro{" +
                "local='" + local + '\'' +
                ", visitante='" + visitante + '\'' +
                '}';
    }
}

package com.example.cajeroautomatico.data;

import java.time.LocalDateTime;
import java.io.Serializable;


public class Cliente implements Serializable {
    private String usuario;
    private String pin;
    private String nombreCompleto;
    private String cuenta;
    private double saldoDisponible;
    private LocalDateTime ultimaSesion;

    public Cliente() {
    }

    public Cliente(String usuario, String pin, String nombreCompleto,
                   String cuenta, double saldoDisponible,
                   LocalDateTime ultimaSesion) {

        this.usuario = usuario;
        this.pin = pin;
        this.nombreCompleto = nombreCompleto;
        this.cuenta = cuenta;
        this.saldoDisponible = saldoDisponible;
        this.ultimaSesion = ultimaSesion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public double getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setSaldoDisponible(double saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }

    public LocalDateTime getUltimaSesion() {
        return ultimaSesion;
    }

    public void setUltimaSesion(LocalDateTime ultimaSesion) {
        this.ultimaSesion = ultimaSesion;
    }
}

package com.example.cajeroautomatico.data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaccion implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private String usuario;
    private String cuenta;
    private LocalDateTime fecha;
    private String tipoTransaccion;
    private double monto;

    public Transaccion() {
    }

    public Transaccion(String usuario, String cuenta, LocalDateTime fecha,
                       String tipoTransaccion, double monto) {
        this.usuario = usuario;
        this.cuenta = cuenta;
        this.fecha = fecha;
        this.tipoTransaccion = tipoTransaccion;
        this.monto = monto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getFechaFormateada() {
        if (fecha == null) {
            return "";
        }
        return fecha.format(FORMATTER);
    }

    public String getMontoFormateado() {
        return String.format("L. %.2f", monto);
    }
}
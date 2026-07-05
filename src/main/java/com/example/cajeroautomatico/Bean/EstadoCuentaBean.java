package com.example.cajeroautomatico.Bean;

import com.example.cajeroautomatico.data.ArchivoEstadoCuenta;
import com.example.cajeroautomatico.data.Transaccion;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Named("estadoCuentaBean")
@ViewScoped
public class EstadoCuentaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private LoginBean loginBean;

    private List<Transaccion> transacciones;
    private String fechaInicio;
    private String fechaFin;

    @PostConstruct
    public void init() {
        cargarTodas();
    }

    public void cargarTodas() {
        fechaInicio = null;
        fechaFin = null;

        if (loginBean == null || loginBean.getCliente() == null) {
            transacciones = new ArrayList<>();
            return;
        }

        ArchivoEstadoCuenta archivoEstadoCuenta = new ArchivoEstadoCuenta();
        transacciones = archivoEstadoCuenta.obtenerPorUsuarioCuenta(
                loginBean.getCliente().getUsuario(),
                loginBean.getCliente().getCuenta()
        );
    }

    public void filtrar() {
        if (loginBean == null || loginBean.getCliente() == null) {
            transacciones = new ArrayList<>();
            return;
        }

        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime inicio = null;
        LocalDateTime fin = null;

        try {
            if (fechaInicio != null && !fechaInicio.trim().isEmpty()) {
                inicio = LocalDate.parse(fechaInicio.trim(), formatoFecha).atStartOfDay();
            }

            if (fechaFin != null && !fechaFin.trim().isEmpty()) {
                fin = LocalDate.parse(fechaFin.trim(), formatoFecha).atTime(LocalTime.MAX);
            }

            if (inicio != null && fin != null && inicio.isAfter(fin)) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(
                                FacesMessage.SEVERITY_ERROR,
                                "Error",
                                "La fecha inicial no puede ser mayor que la fecha final."
                        )
                );
                return;
            }

            ArchivoEstadoCuenta archivoEstadoCuenta = new ArchivoEstadoCuenta();
            transacciones = archivoEstadoCuenta.obtenerPorRangoFechas(
                    loginBean.getCliente().getUsuario(),
                    loginBean.getCliente().getCuenta(),
                    inicio,
                    fin
            );

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Error",
                            "Ingrese fechas válidas."
                    )
            );
        }
    }

    public void limpiarFiltro() {
        cargarTodas();
    }

    public List<Transaccion> getTransacciones() {
        if (transacciones == null) {
            transacciones = new ArrayList<>();
        }
        return transacciones;
    }

    public void setTransacciones(List<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }
}
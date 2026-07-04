package com.example.cajeroautomatico.Bean;

import com.example.cajeroautomatico.data.Cliente;
import com.example.cajeroautomatico.data.ArchivoClientes;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import jakarta.inject.Named;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Named("LoginBean")
@SessionScoped

public class LoginBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String usuario;
    private String pin;
    private Cliente cliente;

    private final ArchivoClientes archivoClientes = new ArchivoClientes();


    public String iniciarSesion() {

        try {
            if (usuario == null || usuario.trim().isEmpty() || 
                pin == null || pin.trim().isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(
                                FacesMessage.SEVERITY_ERROR,
                                "Error - Usuario / Pin son requeridos",
                                "Usuario y PIN son requeridos."
                        )
                );
                return null;
            }

            cliente = archivoClientes.validarLogin(usuario.trim(), pin.trim());

            if (cliente != null) {
                cliente.setUltimaSesion(LocalDateTime.now());
                archivoClientes.actualizarCliente(cliente);
                System.out.println("LOGIN EXITOSO: " + usuario);
                return "menu?faces-redirect=true";
            }

            System.out.println("LOGIN FALLIDO: Usuario o PIN incorrectos para: " + usuario);
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Error - Usuario / Pin incorrectos",
                            "Usuario o PIN incorrectos."
                    )
            );

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Error",
                            "Error en el sistema de autenticación: " + e.getMessage()
                    )
            );
        }

        return null;
    }

    public String cerrarSesion() {

        FacesContext.getCurrentInstance()
                .getExternalContext()
                .invalidateSession();

        return "/Login.xhtml?faces-redirect=true";
    }

    // Getters y Setters

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArchivoClientes getArchivoClientes() {
        return archivoClientes;
    }

    public String getUltimaSesionFormato() {
        if (cliente != null && cliente.getUltimaSesion() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm");
            return cliente.getUltimaSesion().format(formatter);
        }
        return "";
    }
}

package com.example.cajeroautomatico.Bean;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import jakarta.inject.Named;
import com.example.cajeroautomatico.data.Cliente;
import com.example.cajeroautomatico.data.ArchivoClientes;

import java.time.LocalDateTime;

@Named
@SessionScoped

public class LoginBean implements Serializable {

    private String usuario;
    private String pin;

    private Cliente cliente;

    private final ArchivoClientes archivoClientes = new ArchivoClientes();

    public String iniciarSesion() {

        cliente = archivoClientes.validarLogin(usuario, pin);

        if (cliente != null) {

            // Actualizar fecha de último acceso
            cliente.setUltimaSesion(LocalDateTime.now());

            archivoClientes.actualizarCliente(cliente);

            return "menu?faces-redirect=true";
        }

        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(
                        FacesMessage.SEVERITY_ERROR,
                        "Error",
                        "Usuario o PIN incorrectos."
                )
        );

        return null;
    }

    public String cerrarSesion() {

        FacesContext.getCurrentInstance()
                .getExternalContext()
                .invalidateSession();

        return "login?faces-redirect=true";
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
}


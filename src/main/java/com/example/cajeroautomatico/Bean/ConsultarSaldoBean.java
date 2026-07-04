package com.example.cajeroautomatico.Bean;

import com.example.cajeroautomatico.data.ArchivoClientes;
import com.example.cajeroautomatico.data.Cliente;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import java.io.Serializable;

@Named
@ViewScoped
public class ConsultarSaldoBean implements Serializable {

    private Cliente cliente;

    @Inject
    private LoginBean loginBean;

    private String cuenta;

    @PostConstruct
    public void init() {
        cargarCliente();
    }

    public void cargarCliente() {
        ArchivoClientes archivoClientes = new ArchivoClientes();

        // Preferir cliente del LoginBean inyectado (session-scoped)
        if (loginBean != null && loginBean.getCliente() != null) {
            cliente = loginBean.getCliente();
            this.cuenta = cliente.getCuenta();
            return;
        }

        // Fallback: buscar en el archivo por usuario de sesión o por la cuenta ya seteada
        java.util.List<Cliente> clientes = archivoClientes.obtenerClientes();
        String usuarioSesion = loginBean != null ? loginBean.getUsuario() : null;

        if (usuarioSesion != null) {
            for (Cliente c : clientes) {
                if (usuarioSesion.equals(c.getUsuario())) {
                    cliente = c;
                    this.cuenta = c.getCuenta();
                    break;
                }
            }
        } else if (this.cuenta != null) {
            for (Cliente c : clientes) {
                if (this.cuenta.equals(c.getCuenta())) {
                    cliente = c;
                    break;
                }
            }
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getNombreCompleto() {
        return cliente != null ? cliente.getNombreCompleto() : "";
    }

    public String getCuenta() {
        if (cliente != null && cliente.getCuenta() != null) return cliente.getCuenta();
        return cuenta != null ? cuenta : "";
    }

    public double getSaldoDisponible() {
        return cliente != null ? cliente.getSaldoDisponible() : 0.0;
    }
}

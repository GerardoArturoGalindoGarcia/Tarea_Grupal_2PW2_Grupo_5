package com.example.cajeroautomatico.Bean;

import com.example.cajeroautomatico.data.ArchivoClientes;
import com.example.cajeroautomatico.data.ArchivoHistorial;
import com.example.cajeroautomatico.data.Cliente;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import com.example.cajeroautomatico.data.ArchivoHistorial;

import java.io.Serializable;

@Named("RetiroBean")
@SessionScoped
public class RetiroBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String pinIngresado;

    @Inject
    private LoginBean loginBean;

    private double montoTransaccion;

    private final ArchivoClientes archivoClientes = new ArchivoClientes();
    private final ArchivoHistorial archivoHistorial = new ArchivoHistorial();

    public String procesarRetiro() {

        Cliente cliente = loginBean.getCliente();

        if (pinIngresado == null || !pinIngresado.equals(cliente.getPin())) {
            jakarta.faces.context.FacesContext.getCurrentInstance().addMessage(null,
                    new jakarta.faces.application.FacesMessage(jakarta.faces.application.FacesMessage.SEVERITY_ERROR,
                            "PIN inválido: El código de seguridad ingresado no es correcto.", null));
            return null;
        }



        try {

            // Validar monto
            if (montoTransaccion <= 0) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(
                                FacesMessage.SEVERITY_ERROR,
                                "Error",
                                "Ingrese un monto mayor a cero."
                        )
                );
                return null;
            }

            // Validar saldo
            if (montoTransaccion > cliente.getSaldoDisponible()) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(
                                FacesMessage.SEVERITY_ERROR,
                                "Error",
                                "Saldo insuficiente."
                        )
                );
                return null;
            }

            // Actualizar saldo
            cliente.setSaldoDisponible(
                    cliente.getSaldoDisponible() - montoTransaccion
            );

            // Guardar cambios
            archivoClientes.actualizarCliente(cliente);

            // Registrar historial
            archivoHistorial.registrarTransaccion(
                    cliente.getUsuario(),
                    cliente.getCuenta(),
                    "RETIRO",
                    montoTransaccion
            );

            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_INFO,
                            "Éxito",
                            "Retiro realizado correctamente."
                    )
            );

            montoTransaccion = 0;

        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Error",
                            e.getMessage()
                    )
            );
        }

        return null;
    }

    //=========================
    // GETTERS Y SETTERS
    //=========================

    public double getMontoTransaccion() {
        return montoTransaccion;
    }

    public void setMontoTransaccion(double montoTransaccion) {
        this.montoTransaccion = montoTransaccion;
    }


    public String getPinIngresado() {
        return pinIngresado;
    }

    public void setPinIngresado(String pinIngresado) {
        this.pinIngresado = pinIngresado;
    }
}

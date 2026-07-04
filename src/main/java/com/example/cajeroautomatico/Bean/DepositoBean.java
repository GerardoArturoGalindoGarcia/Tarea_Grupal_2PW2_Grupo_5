package com.example.cajeroautomatico.Bean;

import com.example.cajeroautomatico.data.ArchivoHistorial;
import com.example.cajeroautomatico.data.ArchivoClientes;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

@Named(value = "depositoBean")
@ViewScoped
public class DepositoBean implements Serializable {

    @Inject
    private LoginBean LoginBean;

    private String pinIngresado;

    private Double montoDeposito;

    private final ArchivoHistorial archivoHistorial = new ArchivoHistorial();
    private final ArchivoClientes archivoClientes = new ArchivoClientes();

    public DepositoBean() {
    }

    public String procesarDeposito() {
        FacesContext context = FacesContext.getCurrentInstance();


        if (LoginBean == null || LoginBean.getCliente() == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error No ha iniciado sesión",
                    "No hay ninguna sesión de cliente activa."));
            return null;
        }


        if (montoDeposito == null || montoDeposito <= 0) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error  - Monto a depositar debe ser mayo a cero",
                    "Monto inválido. Debe ser mayor a cero."));
            return null;
        }

        try {
            Object clienteActual = LoginBean.getCliente();


            String pinCliente = (String) clienteActual.getClass().getMethod("getPin").invoke(clienteActual);
            if (pinIngresado == null || !pinIngresado.equals(pinCliente)) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "PIN inválido: El código de seguridad ingresado no es correcto.",
                        "PIN inválido."));
                return null;
            }


            double saldoActual = (double) clienteActual.getClass().getMethod("getSaldoDisponible").invoke(clienteActual);
            double nuevoSaldo = saldoActual + montoDeposito;

            clienteActual.getClass().getMethod("setSaldoDisponible", double.class).invoke(clienteActual, nuevoSaldo);


            archivoClientes.getClass().getMethod("actualizarCliente", clienteActual.getClass()).invoke(archivoClientes, clienteActual);


            String usuario = (String) clienteActual.getClass().getMethod("getUsuario").invoke(clienteActual);
            String cuenta = (String) clienteActual.getClass().getMethod("getCuenta").invoke(clienteActual);


            archivoHistorial.registrarTransaccion(usuario, cuenta, "DEPOSITO", montoDeposito);


            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Depósito realizado correctamente. Nuevo saldo: L. " + nuevoSaldo));


            this.montoDeposito = null;
            this.pinIngresado = "";

        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al procesar el depósito en la persistencia."));
        }

        return null;
    }


    public String getPinIngresado() { return pinIngresado; }
    public void setPinIngresado(String pinIngresado) { this.pinIngresado = pinIngresado; }

    // CAMBIO: Tipos de datos actualizados a Double
    public Double getMontoDeposito() { return montoDeposito; }
    public void setMontoDeposito(Double montoDeposito) { this.montoDeposito = montoDeposito; }

    public LoginBean getLoginBean() { return LoginBean; }
    public void setLoginBean(LoginBean LoginBean) { this.LoginBean = LoginBean; }
}
package com.example.cajeroautomatico.data;

import jakarta.faces.context.FacesContext;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ArchivoClientes implements Serializable {

    // Ruta del archivo dentro de WEB-INF
    private final String RUTA = "/WEB-INF/cliente.txt";

    // Formato de fecha
    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Lee todos los clientes del archivo.
     */
    public List<Cliente> obtenerClientes() {

        List<Cliente> clientes = new ArrayList<>();

        InputStream is = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getResourceAsStream(RUTA);

        if (is == null) {
            System.out.println("ERROR: No se encontró el archivo " + RUTA);
            return clientes;
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(",");

                Cliente cliente = new Cliente();

                cliente.setUsuario(datos[0]);
                cliente.setPin(datos[1]);
                cliente.setNombreCompleto(datos[2]);
                cliente.setCuenta(datos[3]);
                cliente.setSaldoDisponible(Double.parseDouble(datos[4]));
                cliente.setUltimaSesion(
                        LocalDateTime.parse(datos[5], formatter)
                );

                clientes.add(cliente);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    /**
     * Valida usuario y PIN.
     */
    public Cliente validarLogin(String usuario, String pin) {

        List<Cliente> clientes = obtenerClientes();

        for (Cliente cliente : clientes) {

            if (cliente.getUsuario().equals(usuario)
                    && cliente.getPin().equals(pin)) {
                return cliente;
            }
        }

        return null;
    }

    /**
     * Busca un cliente por número de cuenta.
     */
    public Cliente buscarCuenta(String cuenta) {

        List<Cliente> clientes = obtenerClientes();

        for (Cliente cliente : clientes) {

            if (cliente.getCuenta().equals(cuenta)) {
                return cliente;
            }
        }

        return null;
    }

    /**
     * Guarda todos los clientes en el archivo.
     */
    public void guardarClientes(List<Cliente> clientes) {

        String realPath = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRealPath(RUTA);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(realPath))) {

            for (Cliente cliente : clientes) {

                bw.write(
                        cliente.getUsuario() + "," +
                                cliente.getPin() + "," +
                                cliente.getNombreCompleto() + "," +
                                cliente.getCuenta() + "," +
                                cliente.getSaldoDisponible() + "," +
                                cliente.getUltimaSesion().format(formatter)
                );

                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Actualiza un cliente.
     */
    public void actualizarCliente(Cliente clienteActualizado) {

        List<Cliente> clientes = obtenerClientes();

        for (Cliente cliente : clientes) {

            if (cliente.getCuenta().equals(clienteActualizado.getCuenta())) {

                cliente.setSaldoDisponible(clienteActualizado.getSaldoDisponible());
                cliente.setUltimaSesion(clienteActualizado.getUltimaSesion());

                break;
            }
        }

        guardarClientes(clientes);
    }
}
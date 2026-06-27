package com.example.cajeroautomatico.data;

import java.io.Serializable;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ArchivoClientes implements Serializable {

    // Ruta del archivo
    private final String RUTA = "src/main/resources/cliente.txt";

    // Formato de fecha
    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Lee todos los clientes del archivo.
     */
    public List<Cliente> obtenerClientes() {


        List<Cliente> clientes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(RUTA))) {

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
                        LocalDateTime.parse(datos[5], formatter));

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
     * Actualiza todos los clientes en el archivo.
     */
    public void guardarClientes(List<Cliente> clientes) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA))) {

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

                cliente.setSaldoDisponible(
                        clienteActualizado.getSaldoDisponible());

                cliente.setUltimaSesion(
                        clienteActualizado.getUltimaSesion());

                break;
            }

        }

        guardarClientes(clientes);

    }



}
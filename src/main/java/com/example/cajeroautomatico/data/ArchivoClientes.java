package com.example.cajeroautomatico.data;

import jakarta.faces.context.FacesContext;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ArchivoClientes implements Serializable {
    private static final long serialVersionUID = 1L;

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

        try {
            InputStream is = FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getResourceAsStream(RUTA);

            if (is == null) {
                System.err.println("ERROR: No se encontró el archivo " + RUTA);
                return clientes;
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

                String linea;
                int lineaNum = 0;

                while ((linea = br.readLine()) != null) {
                    lineaNum++;
                    
                    if (linea.trim().isEmpty()) {
                        continue;
                    }

                    try {
                        String[] datos = linea.split(",");

                        if (datos.length < 6) {
                            System.err.println("ERROR en línea " + lineaNum + ": Datos insuficientes. Esperados 6, encontrados " + datos.length);
                            continue;
                        }

                        Cliente cliente = new Cliente();
                        cliente.setUsuario(datos[0].trim());
                        cliente.setPin(datos[1].trim());
                        cliente.setNombreCompleto(datos[2].trim());
                        cliente.setCuenta(datos[3].trim());
                        
                        try {
                            cliente.setSaldoDisponible(Double.parseDouble(datos[4].trim()));
                        } catch (NumberFormatException e) {
                            System.err.println("ERROR en línea " + lineaNum + ": Saldo inválido: " + datos[4]);
                            continue;
                        }
                        
                        try {
                            cliente.setUltimaSesion(
                                    LocalDateTime.parse(datos[5].trim(), formatter)
                            );
                        } catch (Exception e) {
                            System.err.println("ERROR en línea " + lineaNum + ": Fecha inválida: " + datos[5]);
                            cliente.setUltimaSesion(LocalDateTime.now());
                        }

                        clientes.add(cliente);
                        System.out.println("Cliente cargado: " + cliente.getUsuario() + " - " + cliente.getNombreCompleto());
                    } catch (Exception e) {
                        System.err.println("ERROR procesando línea " + lineaNum + ": " + e.getMessage());
                        e.printStackTrace();
                    }
                }

                System.out.println("Total clientes cargados: " + clientes.size());

            } catch (IOException e) {
                System.err.println("ERROR leyendo archivo: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.err.println("ERROR en obtenerClientes: " + e.getMessage());
            e.printStackTrace();
        }

        return clientes;
    }

    /**
     * Valida usuario y PIN.
     */
    public Cliente validarLogin(String usuario, String pin) {
        
        if (usuario == null || pin == null) {
            System.err.println("ERROR: Usuario o PIN es null");
            return null;
        }

        List<Cliente> clientes = obtenerClientes();
        
        System.out.println("Validando login - Usuario: '" + usuario + "', PIN: '" + pin + "'");
        System.out.println("Clientes disponibles: " + clientes.size());

        for (Cliente cliente : clientes) {
            System.out.println("Comparando con: '" + cliente.getUsuario() + "' / '" + cliente.getPin() + "'");
            
            if (cliente.getUsuario() != null && cliente.getPin() != null &&
                cliente.getUsuario().equals(usuario) &&
                cliente.getPin().equals(pin)) {
                System.out.println("LOGIN VALIDADO para: " + usuario);
                return cliente;
            }
        }
        
        System.out.println("LOGIN NO ENCONTRADO para: " + usuario);
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
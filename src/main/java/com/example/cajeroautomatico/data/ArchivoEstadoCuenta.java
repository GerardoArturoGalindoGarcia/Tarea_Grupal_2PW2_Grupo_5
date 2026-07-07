package com.example.cajeroautomatico.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ArchivoEstadoCuenta {

    private static String getFilePath() {
        String basePath = new File("").getAbsolutePath();
        String filePath = new File(basePath, "HISTORIAL.TXT").getAbsolutePath();
        return filePath;
    }

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public List<Transaccion> obtenerTodas() {
        List<Transaccion> lista = new ArrayList<>();
        File archivo = new File(getFilePath());

        if (!archivo.exists()) {
            System.out.println("No existe HISTORIAL.TXT todavía. Estado de cuenta vacío.");
            return lista;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int numeroLinea = 0;

            while ((linea = br.readLine()) != null) {
                numeroLinea++;

                if (linea.trim().isEmpty()) {
                    continue;
                }

                Transaccion transaccion = parsearLinea(linea, numeroLinea);
                if (transaccion != null) {
                    lista.add(transaccion);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer HISTORIAL.TXT: " + e.getMessage());
        }

        return lista;
    }

    public List<Transaccion> obtenerPorUsuarioCuenta(String usuario, String cuenta) {
        List<Transaccion> resultado = new ArrayList<>();

        if (usuario == null || cuenta == null) {
            return resultado;
        }

        for (Transaccion transaccion : obtenerTodas()) {
            if (usuario.equals(transaccion.getUsuario()) && cuenta.equals(transaccion.getCuenta())) {
                resultado.add(transaccion);
            }
        }

        return resultado;
    }

    public List<Transaccion> obtenerPorRangoFechas(String usuario, String cuenta,
                                                   LocalDateTime fechaInicio,
                                                   LocalDateTime fechaFin) {
        List<Transaccion> resultado = new ArrayList<>();

        for (Transaccion transaccion : obtenerPorUsuarioCuenta(usuario, cuenta)) {
            LocalDateTime fechaTransaccion = transaccion.getFecha();

            if (fechaTransaccion == null) {
                continue;
            }

            boolean cumpleInicio = fechaInicio == null || !fechaTransaccion.isBefore(fechaInicio);
            boolean cumpleFin = fechaFin == null || !fechaTransaccion.isAfter(fechaFin);

            if (cumpleInicio && cumpleFin) {
                resultado.add(transaccion);
            }
        }

        return resultado;
    }

    private Transaccion parsearLinea(String linea, int numeroLinea) {
        String[] datos = linea.split(",");

        if (datos.length < 5) {
            System.err.println("Error en HISTORIAL.TXT línea " + numeroLinea + ": se esperaban 5 campos.");
            return null;
        }

        try {
            String usuario = datos[0].trim();
            String cuenta = datos[1].trim();
            LocalDateTime fecha = LocalDateTime.parse(datos[2].trim(), FORMATTER);
            String tipoTransaccion = datos[3].trim();
            double monto = Double.parseDouble(datos[4].trim());

            return new Transaccion(usuario, cuenta, fecha, tipoTransaccion, monto);
        } catch (Exception e) {
            System.err.println("Error leyendo HISTORIAL.TXT línea " + numeroLinea + ": " + e.getMessage());
            return null;
        }
    }
}

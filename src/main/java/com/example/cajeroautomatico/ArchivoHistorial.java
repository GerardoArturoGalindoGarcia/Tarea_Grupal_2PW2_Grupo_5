package com.example.cajeroautomatico.data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ArchivoHistorial {

    // Se guardará en la raíz del proyecto o donde corra el servidor Tomcat
    private static final String FILE_PATH = "HISTORIAL.TXT";

    public void registrarTransaccion(String usuario, String cuenta, String tipo, double monto) {
        // Formato de fecha estándar (Año-Mes-Día Hora:Minutos:Segundos)
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // Estructura acordada: USUARIO,CUENTA,FECHA,TIPO_TRANSACCION,MONTO
        String linea = String.format("%s,%s,%s,%s,%.2f", usuario, cuenta, fecha, tipo, monto);

        // El parámetro 'true' habilita el modo Append para añadir líneas al final sin borrar lo previo
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(linea);
            bw.newLine();
            System.out.println("HISTORIAL REGISTRADO: " + tipo + " de L. " + monto + " para la cuenta " + cuenta);
        } catch (IOException e) {
            System.err.println("Error al escribir en HISTORIAL.TXT: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
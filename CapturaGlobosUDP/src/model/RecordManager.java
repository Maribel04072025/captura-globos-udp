/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * Autor: Maribel Ceballos
 *
 * Clase encargada de manejar el record del juego.
 * Permite guardar y consultar el puntaje maximo obtenido.
 *
 * El record se almacena en un archivo de texto local.
 *
 * Responsabilidades:
 * - Leer el record actual desde archivo
 * - Guardar un nuevo record si es mayor
 */

import java.io.*;

public class RecordManager {

    private static final String FILE = "record.txt";

    /**
     * Obtiene el record actual del sistema.
     * Si no existe el archivo, retorna 0.
     *
     * @return record actual
     */
    public static int getRecord() {

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            return Integer.parseInt(br.readLine());
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Guarda un nuevo record si el puntaje es mayor al existente.
     *
     * @param puntos puntaje obtenido en la partida
     */
    public static void saveRecord(int puntos) {

        int recordActual = getRecord();

        if (puntos > recordActual) {
            try (PrintWriter pw = new PrintWriter(new FileWriter(FILE))) {
                pw.println(puntos);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

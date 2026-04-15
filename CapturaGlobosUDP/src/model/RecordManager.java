/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.*;

public class RecordManager {

    private static final String FILE = "record.txt";

    // 🔥 obtener récord actual
    public static int getRecord() {

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            return Integer.parseInt(br.readLine());
        } catch (Exception e) {
            return 0; // si no existe archivo
        }
    }

    // 🔥 guardar récord si es mayor
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

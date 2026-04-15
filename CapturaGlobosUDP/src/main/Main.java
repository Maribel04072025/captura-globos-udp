/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 * Autor: Maribel Ceballos
 *
 * Clase principal de ejecucion del programa.
 * Es el punto de entrada del sistema y se encarga
 * de iniciar la interfaz grafica del juego.
 *
 * Utiliza SwingUtilities.invokeLater para asegurar
 * que la interfaz se ejecute en el hilo correcto de Swing.
 */

import ui.PantallaInicio;

public class Main {

    /**
     * Metodo principal del programa.
     * Inicia la aplicacion lanzando la pantalla de inicio.
     *
     * @param args argumentos de linea de comandos
     */
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> {
            new PantallaInicio();
        });

    }
}

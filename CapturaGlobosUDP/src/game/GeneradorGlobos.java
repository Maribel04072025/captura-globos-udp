/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 * Autor: Maribel Ceballos
 *
 * Clase encargada de generar globos automaticamente durante la partida.
 * Utiliza un Timer para crear globos en intervalos de tiempo
 * y evita colisiones entre ellos.
 *
 * Responsabilidades:
 * - Generar globos aleatorios en pantalla
 * - Evitar superposicion de globos
 * - Controlar inicio y detencion del generador
 */

import model.Globo;

import javax.swing.*;
import java.util.Random;

public class GeneradorGlobos {

    private Timer timer;
    private Random random = new Random();
    private GameManager manager;

    /**
     * Crea un generador asociado al GameManager.
     *
     * @param manager gestor principal del juego
     */
    public GeneradorGlobos(GameManager manager) {
        this.manager = manager;
    }

    /**
     * Inicia la generacion de globos.
     * Detiene cualquier generador anterior por seguridad.
     */
    public void iniciar() {
        detener();

        timer = new Timer(600, e -> generar());
        timer.start();
    }

    /**
     * Detiene la generacion de globos si esta activa.
     */
    public void detener() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }

    /**
     * Genera un nuevo globo en una posicion valida.
     * Evita colisiones con otros globos existentes.
     */
    private void generar() {

        JPanel zona = manager.getZonaJuego();

        if (zona == null || zona.getWidth() <= 0 || zona.getHeight() <= 0) return;

        int radio = 80;
        int intentos = 0;

        while (intentos < 20) {

            int x = random.nextInt(zona.getWidth() - radio * 2) + radio;
            int y = random.nextInt(zona.getHeight() - radio * 2) + radio;

            Globo nuevo = new Globo(x, y, radio);

            if (!manager.getColisionador().hayColision(nuevo)) {
                manager.agregarGlobo(nuevo);
                break;
            }

            intentos++;
        }
    }
}

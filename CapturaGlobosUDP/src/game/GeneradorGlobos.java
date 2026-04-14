/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import model.Globo;

import javax.swing.*;
import java.util.Random;

public class GeneradorGlobos {

    private Timer timer;
    private Random random = new Random();
    private GameManager manager;

    public GeneradorGlobos(GameManager manager) {
        this.manager = manager;
    }

    // 🔥 Inicia SOLO si no hay uno corriendo
    public void iniciar() {
        detener(); // por seguridad

        timer = new Timer(600, e -> generar());
        timer.start();
    }

    // 🔥 CLAVE TOTAL PARA QUE "Volver a jugar" funcione
    public void detener() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }

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

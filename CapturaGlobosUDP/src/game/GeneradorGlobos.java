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

    public void iniciar() {

        timer = new Timer(1500, e -> generar());
        timer.start();
    }

    private void generar() {

        var estado = GameManager.getInstance().getEstado();

        if (GameManager.getInstance().getZonaJuego() == null) return;

        int radio = 40;

        int ancho = GameManager.getInstance().getZonaJuego().getWidth();
        int alto = GameManager.getInstance().getZonaJuego().getHeight();

        if (ancho == 0 || alto == 0) return;

        int x = random.nextInt(ancho - radio * 2) + radio;
        int y = random.nextInt(alto - radio * 2) + radio;

        Globo g = new Globo(x, y, radio);
        estado.getGlobos().add(g);

        System.out.println("Globo generado en: " + x + "," + y);
    }

    public void detener() {
        if (timer != null) timer.stop();
    }
}


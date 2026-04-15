/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Globo {

    private int x, y, radio;
    private int dx, dy;
    private Image imagen;

    private static final String[] RUTAS = {
            "/recursos/globo.png",
            "/recursos/globoAmarillo.png",
            "/recursos/globoAzul.png"
    };

    private static final Random random = new Random();

    public Globo(int x, int y, int radio) {
        this.x = x;
        this.y = y;
        this.radio = radio;

        dx = random.nextInt(5) + 2;
        dy = random.nextInt(5) + 2;

        if (random.nextBoolean()) dx *= -1;
        if (random.nextBoolean()) dy *= -1;

        // 🔥 Imagen aleatoria
        String ruta = RUTAS[random.nextInt(RUTAS.length)];
        imagen = new ImageIcon(getClass().getResource(ruta)).getImage();
    }

    public void mover(int ancho, int alto) {
        x += dx;
        y += dy;

        if (x - radio < 0 || x + radio > ancho) dx *= -1;
        if (y - radio < 0 || y + radio > alto) dy *= -1;
    }

    public void dibujar(Graphics g) {
        g.drawImage(imagen, x - radio, y - radio, radio * 2, radio * 2, null);
    }

    public boolean contiene(int mx, int my) {
        int dx = mx - x;
        int dy = my - y;
        return dx * dx + dy * dy <= radio * radio;
    }

    public boolean colisionaCon(Globo otro) {
        int dx = this.x - otro.x;
        int dy = this.y - otro.y;
        int r = this.radio + otro.radio;
        return dx * dx + dy * dy <= r * r;
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javax.swing.*;
import java.awt.*;

public class Globo {

    private int x, y, radio;
    private Image imagen;

    public Globo(int x, int y, int radio) {
        this.x = x;
        this.y = y;
        this.radio = radio;

        // Cargar imagen UNA sola vez por globo
        imagen = new ImageIcon(
                getClass().getResource("/recursos/globo.png")
        ).getImage();
    }

    public void dibujar(Graphics g) {
        g.drawImage(imagen,
                x - radio,
                y - radio,
                radio * 2,
                radio * 2,
                null);
    }

    public boolean contiene(int mx, int my) {
        int dx = mx - x;
        int dy = my - y;
        return dx * dx + dy * dy <= radio * radio;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getRadio() { return radio; }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author marib
 */
import java.awt.Color;

public class Globo {

    private int x;
    private int y;
    private int radio;
    private Color color;

    public Globo(int x, int y, int radio, Color color) {
        this.x = x;
        this.y = y;
        this.radio = radio;
        this.color = color;
    }

    public boolean contiene(int mx, int my) {
        int dx = mx - x;
        int dy = my - y;
        return dx * dx + dy * dy <= radio * radio;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getRadio() { return radio; }
    public Color getColor() { return color; }
}

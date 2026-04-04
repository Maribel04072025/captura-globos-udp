/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Color;

public class Globo {

    private int x, y, radio;
    private Color color;

    public Globo(int x, int y, int radio, Color color) {
        this.x = x;
        this.y = y;
        this.radio = radio;
        this.color = color;
    }

    public boolean contiene(int px, int py) {
        int dx = px - x;
        int dy = py - y;
        return dx * dx + dy * dy <= radio * radio;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getRadio() { return radio; }
    public Color getColor() { return color; }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * Autor: Maribel Ceballos
 *
 * Clase que representa un globo dentro del juego.
 * Cada globo tiene posicion, movimiento, imagen y valor de puntaje.
 *
 * Responsabilidades principales:
 * - Representar un objeto interactivo dentro del juego
 * - Manejar posicion y movimiento del globo
 * - Detectar colisiones con clicks y otros globos
 * - Asignar un valor de puntaje segun su tipo
 */

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Globo {

    private double x, y;
    private int radio;

    private double dx, dy;
    private Image imagen;

    private int valor;

    private static final String[] RUTAS = {
            "/recursos/globo.png",
            "/recursos/globoAmarillo.png",
            "/recursos/globoAzul.png"
    };

    private static final Random random = new Random();

    /**
     * Constructor del globo.
     * Inicializa posicion, velocidad, imagen y valor.
     */
    public Globo(int x, int y, int radio) {
        this.x = x;
        this.y = y;
        this.radio = radio;

        dx = random.nextInt(5) + 2;
        dy = random.nextInt(5) + 2;

        if (random.nextBoolean()) dx *= -1;
        if (random.nextBoolean()) dy *= -1;

        String ruta = RUTAS[random.nextInt(RUTAS.length)];
        imagen = new ImageIcon(getClass().getResource(ruta)).getImage();

        asignarValorPorTipo(ruta);
    }

    /**
     * Asigna el valor del globo segun su tipo.
     */
    private void asignarValorPorTipo(String ruta) {

        if (ruta.contains("globo.png")) {
            valor = 1;
        } else if (ruta.contains("Amarillo")) {
            valor = 3;
        } else if (ruta.contains("Azul")) {
            valor = 5;
        } else {
            valor = 1;
        }
    }

    /**
     * Retorna el valor en puntos del globo.
     */
    public int getValor() {
        return valor;
    }

    /**
     * Mueve el globo dentro del area de juego aplicando velocidad global.
     */
    public void mover(int ancho, int alto, double velocidadGlobal) {

        x += dx * velocidadGlobal;
        y += dy * velocidadGlobal;

        if (x - radio < 0 || x + radio > ancho) dx *= -1;
        if (y - radio < 0 || y + radio > alto) dy *= -1;
    }

    /**
     * Dibuja el globo en pantalla.
     */
    public void dibujar(Graphics g) {
        g.drawImage(
                imagen,
                (int)(x - radio),
                (int)(y - radio),
                radio * 2,
                radio * 2,
                null
        );
    }

    /**
     * Verifica si un punto colisiona con el globo.
     */
    public boolean contiene(int mx, int my) {
        double dx = mx - x;
        double dy = my - y;
        return dx * dx + dy * dy <= radio * radio;
    }

    /**
     * Verifica colision con otro globo.
     */
    public boolean colisionaCon(Globo otro) {
        double dx = this.x - otro.x;
        double dy = this.y - otro.y;
        double r = this.radio + otro.radio;
        return dx * dx + dy * dy <= r * r;
    }
}
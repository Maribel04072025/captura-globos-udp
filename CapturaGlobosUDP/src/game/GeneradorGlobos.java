/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import model.Globo;
import model.EstadoJuego;
import util.Constantes;

import java.awt.Color;

public class GeneradorGlobos {

    private EstadoJuego estado;

    public GeneradorGlobos(EstadoJuego estado) {
        this.estado = estado;
    }

    public void generar() {
        for (int i = 0; i < Constantes.GLOBOS_POR_CICLO; i++) {
            crearGlobo();
        }
    }

    private void crearGlobo() {

        int radio = Constantes.RADIO_GLOBO;
        int intentos = 0;
        boolean posicionValida = false;

        int x = 0, y = 0;

        while (!posicionValida && intentos < 50) {

            x = (int) (Math.random() * (Constantes.ANCHO_VENTANA - 2 * Constantes.MARGEN)) + Constantes.MARGEN;
            y = (int) (Math.random() * (Constantes.ALTO_VENTANA - 2 * Constantes.MARGEN)) + Constantes.MARGEN;

            posicionValida = true;

            for (Globo g : estado.getGlobos()) {
                int dx = g.getX() - x;
                int dy = g.getY() - y;
                double distancia = Math.sqrt(dx * dx + dy * dy);

                if (distancia < radio * 2) {
                    posicionValida = false;
                    break;
                }
            }

            intentos++;
        }

        if (posicionValida) {
            Color color = new Color(
                    (int)(Math.random()*255),
                    (int)(Math.random()*255),
                    (int)(Math.random()*255)
            );

            estado.getGlobos().add(new Globo(x, y, radio, color));
        }
    }
}
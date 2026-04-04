/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import model.EstadoJuego;
import model.Globo;

import java.util.Iterator;

public class Colisionador {

    private EstadoJuego estado;

    public Colisionador(EstadoJuego estado) {
        this.estado = estado;
    }

    public void procesarClick(int x, int y) {

        Iterator<Globo> it = estado.getGlobos().iterator();

        while (it.hasNext()) {
            Globo g = it.next();

            if (g.contiene(x, y)) {
                it.remove();
                estado.getJugadorLocal().sumarPunto();
                break;
            }
        }
    }
}
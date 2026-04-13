/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import model.EstadoJuego;
import model.Globo;

import java.util.List;

public class Colisionador {

    private EstadoJuego estado;

    public Colisionador(EstadoJuego estado) {
        this.estado = estado;
    }

    public void procesarClick(int x, int y) {
        estado.reventarGloboEn(x, y);
    }

    public boolean hayColision(Globo nuevo) {

        List<Globo> globos = estado.getGlobos();

        for (Globo g : globos) {
            if (g.colisionaCon(nuevo)) {
                return true;
            }
        }

        return false;
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import model.EstadoJuego;
import model.Globo;

import java.util.List;

/**
 * Encargado de gestionar la deteccion de colisiones en el juego.
 * Su funcion principal es evitar que los globos se generen
 * superpuestos y procesar los clicks del usuario sobre ellos.
 */
public class Colisionador {

    private EstadoJuego estado;

    /**
     * Crea un colisionador asociado a un estado de juego.
     *
     * @param estado estado actual del juego que contiene los globos
     */
    public Colisionador(EstadoJuego estado) {
        this.estado = estado;
    }

    /**
     * Procesa un click del usuario y delega la logica
     * al estado del juego para reventar un globo.
     *
     * @param x posicion en eje X del click
     * @param y posicion en eje Y del click
     */
    public void procesarClick(int x, int y) {
        estado.reventarGloboEn(x, y);
    }

    /**
     * Verifica si un nuevo globo colisiona con alguno ya existente.
     * Se usa para evitar que los globos aparezcan superpuestos.
     *
     * @param nuevo globo que se desea validar
     * @return true si hay colision con otro globo, false si no hay colision
     */
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
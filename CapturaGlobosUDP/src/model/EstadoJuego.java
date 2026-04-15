/**
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 
 /**
 * 
 */
package model;

/**
 * Autor: Maribel Ceballos
 *
 * Clase que representa el estado general del juego.
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import sound.SoundManager;

public class EstadoJuego {

    private List<Globo> globos = new ArrayList<>();
    private Jugador jugadorLocal;
    private Jugador jugadorRemoto;
    private int tiempoRestante = 60;

    // 🔥 velocidad global del juego
    private double velocidadGlobal = 1.0;

    public List<Globo> getGlobos() {
        return globos;
    }

    public void agregarGlobo(Globo g) {
        globos.add(g);
    }

    // 🔥 AHORA USA VELOCIDAD GLOBAL
    public void actualizarGlobos(int ancho, int alto) {

        for (Globo g : globos) {
            g.mover(ancho, alto, velocidadGlobal);
        }
    }

    public void reventarGloboEn(int x, int y) {

        Iterator<Globo> it = globos.iterator();

        while (it.hasNext()) {

            Globo g = it.next();

            if (g.contiene(x, y)) {
                it.remove();

                if (jugadorLocal != null) {
                    jugadorLocal.sumarPuntos(g.getValor());
                }

                SoundManager.getInstance().sonidoPop();
                break;
            }
        }
    }

    // 🔥 dificultad progresiva
    public void bajarTiempo() {

        if (tiempoRestante > 0) {
            tiempoRestante--;

            if (tiempoRestante % 10 == 0) {
                velocidadGlobal += 0.15;
            }
        }
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public Jugador getJugadorLocal() {
        return jugadorLocal;
    }

    public void setJugadorLocal(Jugador jugadorLocal) {
        this.jugadorLocal = jugadorLocal;
    }

    public Jugador getJugadorRemoto() {
        return jugadorRemoto;
    }

    public void setJugadorRemoto(Jugador jugadorRemoto) {
        this.jugadorRemoto = jugadorRemoto;
    }
}
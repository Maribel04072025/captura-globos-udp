/**
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 
 /**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EstadoJuego {

    private List<Globo> globos = new ArrayList<>();
    private Jugador jugadorLocal;
    private Jugador jugadorRemoto;
    private int tiempoRestante = 60;

    // ---------------- GLOBOS ----------------

    public List<Globo> getGlobos() {
        return globos;
    }

    public void agregarGlobo(Globo g) {
        globos.add(g);
    }

    public void actualizarGlobos(int ancho, int alto) {
        for (Globo g : globos) {
            g.mover(ancho, alto);
        }
    }

    public void reventarGloboEn(int x, int y) {
        Iterator<Globo> it = globos.iterator();

        while (it.hasNext()) {
            Globo g = it.next();

            if (g.contiene(x, y)) {
                it.remove();

                if (jugadorLocal != null) {
                    jugadorLocal.sumarPunto();
                }

                sound.SoundManager.getInstance().sonidoPop();
                break;
            }
        }
    }

    // ---------------- TIEMPO ----------------

    public void bajarTiempo() {
        if (tiempoRestante > 0) {
            tiempoRestante--;
        }
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    // ---------------- JUGADORES ----------------

    public Jugador getJugadorLocal() { return jugadorLocal; }

    public void setJugadorLocal(Jugador jugadorLocal) {
        this.jugadorLocal = jugadorLocal;
    }

    public Jugador getJugadorRemoto() { return jugadorRemoto; }

    public void setJugadorRemoto(Jugador jugadorRemoto) {
        this.jugadorRemoto = jugadorRemoto;
    }
}
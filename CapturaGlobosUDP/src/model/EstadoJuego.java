/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author marib
 */
import java.util.ArrayList;
import java.util.List;

public class EstadoJuego {

    private List<Globo> globos = new ArrayList<>();
    private Jugador jugadorLocal;
    private Jugador jugadorRemoto;
    private int tiempoRestante = 60;

    public List<Globo> getGlobos() {
        return globos;
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

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public void setTiempoRestante(int tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
    }
}

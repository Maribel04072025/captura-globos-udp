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
 * Controla los elementos activos como globos, jugadores
 * y el tiempo restante de la partida.
 *
 * Responsabilidades:
 * - Gestionar lista de globos activos
 * - Controlar puntaje del jugador
 * - Manejar tiempo del juego
 * - Procesar interacciones (clicks sobre globos)
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

    // ---------------- GLOBOS ----------------

    /**
     * Retorna la lista de globos activos en el juego.
     */
    public List<Globo> getGlobos() {
        return globos;
    }

    /**
     * Agrega un nuevo globo al estado del juego.
     */
    public void agregarGlobo(Globo g) {
        globos.add(g);
    }

    /**
     * Actualiza la posicion de todos los globos activos.
     */
    public void actualizarGlobos(int ancho, int alto) {
        for (Globo g : globos) {
            g.mover(ancho, alto);
        }
    }

    /**
     * Procesa el click del jugador y elimina el globo si es golpeado.
     * Ademas suma puntos segun el valor del globo.
     */
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

    // ---------------- TIEMPO ----------------

    /**
     * Reduce el tiempo restante del juego en 1 segundo.
     */
    public void bajarTiempo() {
        if (tiempoRestante > 0) {
            tiempoRestante--;
        }
    }

    /**
     * Retorna el tiempo restante de la partida.
     */
    public int getTiempoRestante() {
        return tiempoRestante;
    }

    // ---------------- JUGADORES ----------------

    /**
     * Retorna el jugador principal.
     */
    public Jugador getJugadorLocal() {
        return jugadorLocal;
    }

    /**
     * Asigna el jugador principal.
     */
    public void setJugadorLocal(Jugador jugadorLocal) {
        this.jugadorLocal = jugadorLocal;
    }

    /**
     * Retorna el jugador secundario.
     */
    public Jugador getJugadorRemoto() {
        return jugadorRemoto;
    }

    /**
     * Asigna el jugador secundario.
     */
    public void setJugadorRemoto(Jugador jugadorRemoto) {
        this.jugadorRemoto = jugadorRemoto;
    }
}
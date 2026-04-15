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
 * Administra todos los elementos activos durante la partida,
 * incluyendo globos, jugadores, tiempo y dificultad.
 *
 * Responsabilidades principales:
 * - Manejar la lista de globos activos en pantalla
 * - Controlar el jugador principal y su puntaje
 * - Administrar el tiempo restante del juego
 * - Aumentar progresivamente la dificultad del juego
 * - Procesar interacciones del usuario (clicks sobre globos)
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

    // Factor que incrementa la velocidad global de los globos
    private double velocidadGlobal = 1.0;

    /**
     * Retorna la lista de globos activos en el juego
     */
    public List<Globo> getGlobos() {
        return globos;
    }

    /**
     * Agrega un nuevo globo al estado del juego
     */
    public void agregarGlobo(Globo g) {
        globos.add(g);
    }

    /**
     * Actualiza la posicion de todos los globos en pantalla
     * aplicando la velocidad global del juego
     */
    public void actualizarGlobos(int ancho, int alto) {

        for (Globo g : globos) {
            g.mover(ancho, alto, velocidadGlobal);
        }
    }

    /**
     * Procesa el click del usuario sobre la pantalla.
     * Si un globo es golpeado, se elimina y se suma puntaje
     * al jugador correspondiente segun el valor del globo.
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

    /**
     * Reduce el tiempo restante del juego.
     * Cada cierto intervalo aumenta la dificultad
     * incrementando la velocidad global de los globos.
     */
    public void bajarTiempo() {

        if (tiempoRestante > 0) {
            tiempoRestante--;

            if (tiempoRestante % 10 == 0) {
                velocidadGlobal += 0.15;
            }
        }
    }

    /**
     * Retorna el tiempo restante del juego
     */
    public int getTiempoRestante() {
        return tiempoRestante;
    }

    /**
     * Retorna el jugador principal
     */
    public Jugador getJugadorLocal() {
        return jugadorLocal;
    }

    /**
     * Asigna el jugador principal
     */
    public void setJugadorLocal(Jugador jugadorLocal) {
        this.jugadorLocal = jugadorLocal;
    }

    /**
     * Retorna el jugador secundario
     */
    public Jugador getJugadorRemoto() {
        return jugadorRemoto;
    }

    /**
     * Asigna el jugador secundario
     */
    public void setJugadorRemoto(Jugador jugadorRemoto) {
        this.jugadorRemoto = jugadorRemoto;
    }
}
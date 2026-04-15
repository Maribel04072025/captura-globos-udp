/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * Autor: Maribel Ceballos
 *
 * Clase que representa un jugador del juego.
 * Almacena informacion basica como nombre y puntaje.
 *
 * Responsabilidades:
 * - Guardar el nombre del jugador
 * - Administrar el puntaje acumulado
 */

public class Jugador {

    private String nombre;
    private int puntos;

    /**
     * Constructor del jugador.
     * Inicializa el puntaje en cero.
     */
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntos = 0;
    }

    /**
     * Suma puntos al jugador segun el valor recibido.
     *
     * @param valor cantidad de puntos a agregar
     */
    public void sumarPuntos(int valor) {
        puntos += valor;
    }

    /**
     * Retorna el nombre del jugador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Retorna el puntaje actual del jugador.
     */
    public int getPuntos() {
        return puntos;
    }
}
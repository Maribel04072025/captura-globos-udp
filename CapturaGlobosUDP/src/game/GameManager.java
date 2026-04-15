/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import model.*;
import sound.SoundManager;

import javax.swing.*;

/**
 * Clase principal que controla el flujo general del juego.
 * Implementa el patron Singleton para garantizar una unica instancia.
 *
 * Se encarga de:
 * - Gestionar el estado del juego
 * - Controlar generacion de globos
 * - Manejar colisiones
 * - Coordinar musica y reinicio del juego
 */
public class GameManager {

    private static GameManager instance;

    private EstadoJuego estado;
    private GeneradorGlobos generador;
    private JPanel zonaJuego;
    private Colisionador colisionador;

    /**
     * Constructor privado para aplicar Singleton.
     * Inicializa el estado del juego y el sistema de colisiones.
     */
    private GameManager() {
        estado = new EstadoJuego();
        colisionador = new Colisionador(estado);
    }

    /**
     * Retorna la instancia unica del GameManager.
     *
     * @return instancia unica del juego
     */
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    /**
     * Inicia una nueva partida del juego.
     * Configura los jugadores, genera globos y activa la musica.
     *
     * @param local jugador principal
     * @param remoto jugador secundario o CPU
     */
    public void iniciarJuego(Jugador local, Jugador remoto) {

        estado.setJugadorLocal(local);
        estado.setJugadorRemoto(remoto);

        generador = new GeneradorGlobos(this);
        generador.iniciar();

        SoundManager.getInstance().musicaFondo();
    }

    /**
     * Reinicia completamente el estado del juego.
     * Detiene generadores, musica y crea un estado limpio.
     */
    public void reiniciarJuego() {

        if (generador != null) {
            generador.detener();
        }

        SoundManager.getInstance().detenerMusica();

        estado = new EstadoJuego();
        colisionador = new Colisionador(estado);
    }

    /**
     * Establece el panel donde se renderiza el juego.
     *
     * @param panel panel grafico del juego
     */
    public void setZonaJuego(JPanel panel) {
        this.zonaJuego = panel;
    }

    /**
     * Retorna el panel del juego.
     *
     * @return panel grafico
     */
    public JPanel getZonaJuego() {
        return zonaJuego;
    }

    /**
     * Retorna el estado actual del juego.
     *
     * @return estado del juego
     */
    public EstadoJuego getEstado() {
        return estado;
    }

    /**
     * Retorna el sistema de colisiones.
     *
     * @return colisionador
     */
    public Colisionador getColisionador() {
        return colisionador;
    }

    /**
     * Agrega un nuevo globo al juego.
     *
     * @param g globo a agregar
     */
    public void agregarGlobo(Globo g) {
        estado.agregarGlobo(g);
    }

    /**
     * Procesa un click del usuario sobre el area de juego.
     *
     * @param x posicion en eje X
     * @param y posicion en eje Y
     */
    public void procesarClick(int x, int y) {
        estado.reventarGloboEn(x, y);
    }
}
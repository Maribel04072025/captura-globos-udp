/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import model.*;
import sound.SoundManager;

import javax.swing.*;

public class GameManager {

    private static GameManager instance;

    private EstadoJuego estado;
    private GeneradorGlobos generador;
    private JPanel zonaJuego;
    private Colisionador colisionador;

    private GameManager() {
        estado = new EstadoJuego();
        colisionador = new Colisionador(estado);
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    // ================= INICIAR JUEGO =================

    public void iniciarJuego(Jugador local, Jugador remoto) {

        estado.setJugadorLocal(local);
        estado.setJugadorRemoto(remoto);

        generador = new GeneradorGlobos(this);
        generador.iniciar();

        SoundManager.getInstance().musicaFondo();
    }

    // ================= REINICIAR =================

    public void reiniciarJuego() {

        if (generador != null) {
            generador.detener();
        }

        SoundManager.getInstance().detenerMusica();

        estado = new EstadoJuego();
        colisionador = new Colisionador(estado);
    }

    // ================= ZONA =================

    public void setZonaJuego(JPanel panel) {
        this.zonaJuego = panel;
    }

    public JPanel getZonaJuego() {
        return zonaJuego;
    }

    // ================= ESTADO =================

    public EstadoJuego getEstado() {
        return estado;
    }

    public Colisionador getColisionador() {
        return colisionador;
    }

    // ================= ACCIONES =================

    public void agregarGlobo(Globo g) {
        estado.agregarGlobo(g);
    }

    public void procesarClick(int x, int y) {
        estado.reventarGloboEn(x, y);
    }
}
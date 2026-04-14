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
        reiniciarEstado();
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    // 🔥 ESTE MÉTODO ES LA CLAVE DE TODO
    public void reiniciarEstado() {
        estado = new EstadoJuego();
        colisionador = new Colisionador(estado);

        // detener generador viejo si existía
        if (generador != null) {
            generador.detener();
        }
    }

    public void iniciarJuego(Jugador local, Jugador remoto) {

        // 🔥 SIEMPRE EMPIEZA LIMPIO
        reiniciarEstado();

        estado.setJugadorLocal(local);
        estado.setJugadorRemoto(remoto);

        generador = new GeneradorGlobos(this);
        generador.iniciar();

        // 🎵 Música SOLO si no está sonando
        SoundManager.getInstance().musicaFondo();
    }

    public void setZonaJuego(JPanel panel) {
        this.zonaJuego = panel;
    }

    public JPanel getZonaJuego() {
        return zonaJuego;
    }

    public EstadoJuego getEstado() {
        return estado;
    }

    public Colisionador getColisionador() {
        return colisionador;
    }

    public void agregarGlobo(Globo g) {
        estado.agregarGlobo(g);
    }

    public void procesarClick(int x, int y) {
        estado.reventarGloboEn(x, y);
    }
}
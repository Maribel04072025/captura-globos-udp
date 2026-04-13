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

    public void iniciarJuego(Jugador local, Jugador remoto) {

        estado.setJugadorLocal(local);
        estado.setJugadorRemoto(remoto);

        generador = new GeneradorGlobos(this);
        generador.iniciar();

        // 🎵 MÚSICA DE FONDO
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

    public void agregarGlobo(model.Globo g) {
        estado.agregarGlobo(g);
    }

    public void procesarClick(int x, int y) {
        estado.reventarGloboEn(x, y);
    }
}
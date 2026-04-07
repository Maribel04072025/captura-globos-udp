/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import model.EstadoJuego;
import model.Globo;
import model.Jugador;

import javax.swing.*;
import java.util.Iterator;

public class GameManager {

    private static GameManager instance;

    private EstadoJuego estado;
    private GeneradorGlobos generador;
    private JPanel zonaJuego;

    private GameManager() {
        estado = new EstadoJuego();
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    // ------------------ INICIO DEL JUEGO ------------------

    public void iniciarJuego(Jugador local, Jugador remoto) {
        estado.setJugadorLocal(local);
        estado.setJugadorRemoto(remoto);

        generador = new GeneradorGlobos();
        generador.iniciar();
    }

    // ------------------ ZONA DE DIBUJO ------------------

    public void setZonaJuego(JPanel panel) {
        this.zonaJuego = panel;
    }

    public JPanel getZonaJuego() {
        return zonaJuego;
    }

    // ------------------ CLIC EN GLOBO ------------------

    public void procesarClick(int x, int y) {

        Iterator<Globo> it = estado.getGlobos().iterator();

        while (it.hasNext()) {
            Globo g = it.next();

            if (g.contiene(x, y)) {
                it.remove();
                estado.getJugadorLocal().sumarPunto();
                System.out.println("Globo reventado!");
                break;
            }
        }
    }

    // ------------------ GET ESTADO ------------------

    public EstadoJuego getEstado() {
        return estado;
    }
}
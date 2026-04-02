/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 *
 * @author marib
 */
import model.*;
import util.RandomUtil;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

public class GameManager {

    private static GameManager instance;

    private EstadoJuego estado;
    private Timer timerGlobos;
    private Timer timerTiempo;

    private GameManager() {
        estado = new EstadoJuego();
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

        iniciarGeneracionGlobos();
        iniciarTiempo();
    }

    private void iniciarGeneracionGlobos() {
        timerGlobos = new Timer();
        timerGlobos.schedule(new TimerTask() {
            @Override
            public void run() {
                crearGlobo();
            }
        }, 0, 2000);
    }

    private void iniciarTiempo() {
        timerTiempo = new Timer();
        timerTiempo.schedule(new TimerTask() {
            @Override
            public void run() {
                int t = estado.getTiempoRestante();
                estado.setTiempoRestante(t - 1);

                if (t <= 0) {
                    finalizarJuego();
                    timerTiempo.cancel();
                    timerGlobos.cancel();
                }
            }
        }, 1000, 1000);
    }

    private void crearGlobo() {
        int x = RandomUtil.randomX();
        int y = RandomUtil.randomY();

        Globo g = new Globo(x, y, 30, Color.RED);
        estado.getGlobos().add(g);
    }

    public void procesarClick(int x, int y) {
        for (Globo g : estado.getGlobos()) {
            if (g.contiene(x, y)) {
                estado.getGlobos().remove(g);
                estado.getJugadorLocal().sumarPunto();
                break;
            }
        }
    }

    private void finalizarJuego() {
        System.out.println("Juego terminado");
    }

    public EstadoJuego getEstado() {
        return estado;
    }
}
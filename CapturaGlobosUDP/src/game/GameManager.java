/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import model.*;
import util.Constantes;

import java.util.Timer;
import java.util.TimerTask;

public class GameManager {

    private static GameManager instance;

    private EstadoJuego estado;
    private Timer timerGlobos;
    private Timer timerTiempo;

    private GeneradorGlobos generador;
    private Colisionador colisionador;

    private GameManager() {
        estado = new EstadoJuego();
        generador = new GeneradorGlobos(estado);
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
        estado.setTiempoRestante(Constantes.TIEMPO_INICIAL);

        iniciarGeneracionGlobos();
        iniciarTiempo();
    }

    private void iniciarGeneracionGlobos() {
        timerGlobos = new Timer();
        timerGlobos.schedule(new TimerTask() {
            @Override
            public void run() {
                generador.generar();
            }
        }, 0, Constantes.INTERVALO_GLOBOS);
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
                }
            }
        }, 1000, 1000);
    }

    private void finalizarJuego() {
        timerGlobos.cancel();
        timerTiempo.cancel();
        System.out.println("Juego terminado");
    }

    public void procesarClick(int x, int y) {
        colisionador.procesarClick(x, y);
    }

    public EstadoJuego getEstado() {
        return estado;
    }
}
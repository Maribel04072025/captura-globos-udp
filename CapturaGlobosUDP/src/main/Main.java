/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import model.Jugador;
import ui.VentanaJuego;

public class Main {

    public static void main(String[] args) {

        VentanaJuego ventana = new VentanaJuego();

        Jugador j1 = new Jugador("Jugador 1");
        Jugador j2 = new Jugador("Jugador 2");

        ventana.iniciarJuego(j1, j2);
    }
}

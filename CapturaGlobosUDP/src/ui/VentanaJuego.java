/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import javax.swing.*;
import java.awt.*;

import game.GameManager;
import model.Jugador;

public class VentanaJuego extends JFrame {

    private PanelJuego panelJuego;

    public VentanaJuego() {
        setTitle("Captura los Globos 🎈");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        panelJuego = new PanelJuego();
        add(panelJuego, BorderLayout.CENTER);

        setVisible(true);
    }

    public void iniciarJuego(Jugador j1, Jugador j2) {
        GameManager.getInstance().iniciarJuego(j1, j2);
    }
}

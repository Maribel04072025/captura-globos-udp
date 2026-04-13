/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import javax.swing.*;
import java.awt.*;
import game.GameManager;

public class BarraInfo extends JPanel {

    private JLabel lblJugador;
    private JLabel lblPuntos;
    private JLabel lblTiempo;

    public BarraInfo() {

        setLayout(new GridLayout(1, 3));
        setPreferredSize(new Dimension(900, 60));
        setBackground(new Color(220, 235, 250));

        lblJugador = crearLabel("Jugador: ");
        lblPuntos = crearLabel("Puntos: 0");
        lblTiempo = crearLabel("Tiempo: 60");

        add(lblJugador);
        add(lblPuntos);
        add(lblTiempo);
    }

    private JLabel crearLabel(String textoInicial) {
        JLabel l = new JLabel(textoInicial, SwingConstants.CENTER);
        l.setFont(new Font("Arial", Font.BOLD, 20));
        l.setForeground(Color.BLACK);
        return l;
    }

    // Este método SOLO lo llama VentanaJuego
    public void actualizar() {

        var estado = GameManager.getInstance().getEstado();

        if (estado.getJugadorLocal() != null) {
            lblJugador.setText("Jugador: " + estado.getJugadorLocal().getNombre());
            lblPuntos.setText("Puntos: " + estado.getJugadorLocal().getPuntos());
        }

        lblTiempo.setText("Tiempo: " + estado.getTiempoRestante());
    }
}
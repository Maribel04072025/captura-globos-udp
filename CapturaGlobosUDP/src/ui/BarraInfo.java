/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;
import javax.swing.*;
import java.awt.*;
import game.GameManager;
/**
 *
 * @author marib
 */


public class BarraInfo extends JPanel {

    private JLabel lblJugador;
    private JLabel lblPuntos;
    private JLabel lblTiempo;

    public BarraInfo() {

        setLayout(new GridLayout(1, 3));
        setPreferredSize(new Dimension(900, 60));
        setBackground(new Color(220, 235, 250)); // azul claro elegante

        lblJugador = crearLabel();
        lblPuntos = crearLabel();
        lblTiempo = crearLabel();

        add(lblJugador);
        add(lblPuntos);
        add(lblTiempo);

        // Timer para actualizar la info cada 100 ms
        new javax.swing.Timer(100, e -> actualizar()).start();
    }

    private JLabel crearLabel() {
        JLabel l = new JLabel("", SwingConstants.CENTER);
        l.setFont(new Font("Arial", Font.BOLD, 20));
        l.setForeground(Color.BLACK);
        return l;
    }

    public void actualizar() {
        var estado = GameManager.getInstance().getEstado();

        if (estado.getJugadorLocal() != null) {
            lblJugador.setText("Jugador: " + estado.getJugadorLocal().getNombre());
            lblPuntos.setText("Puntos: " + estado.getJugadorLocal().getPuntos());
            lblTiempo.setText("Tiempo: " + estado.getTiempoRestante());
        }
    }

    
}

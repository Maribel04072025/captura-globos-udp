/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

/**
 * Autor: Maribel Ceballos
 *
 * Clase que representa la barra de informacion del juego.
 * Muestra datos en tiempo real como:
 * - Nombre del jugador
 * - Puntaje actual
 * - Tiempo restante
 *
 * Esta barra se actualiza desde la VentanaJuego.
 */

import javax.swing.*;
import java.awt.*;
import game.GameManager;

public class BarraInfo extends JPanel {

    private JLabel lblJugador;
    private JLabel lblPuntos;
    private JLabel lblTiempo;

    /**
     * Constructor de la barra de informacion.
     * Inicializa los labels y el diseño visual.
     */
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

    /**
     * Crea un JLabel con estilo uniforme.
     */
    private JLabel crearLabel(String textoInicial) {
        JLabel l = new JLabel(textoInicial, SwingConstants.CENTER);
        l.setFont(new Font("Arial", Font.BOLD, 20));
        l.setForeground(Color.BLACK);
        return l;
    }

    /**
     * Actualiza los datos mostrados en la barra.
     * Este metodo es llamado desde la VentanaJuego.
     */
    public void actualizar() {

        var estado = GameManager.getInstance().getEstado();

        if (estado.getJugadorLocal() != null) {
            lblJugador.setText("Jugador: " + estado.getJugadorLocal().getNombre());
            lblPuntos.setText("Puntos: " + estado.getJugadorLocal().getPuntos());
        }

        lblTiempo.setText("Tiempo: " + estado.getTiempoRestante());
    }
}
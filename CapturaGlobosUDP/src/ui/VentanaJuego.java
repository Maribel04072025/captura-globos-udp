/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import javax.swing.*;
import java.awt.*;

public class VentanaJuego extends JFrame {

    private CardLayout cardLayout;
    private JPanel contenedor;

    public VentanaJuego() {
        setTitle("🎈 Captura los Globos");
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);

        // Panel de inicio
        JPanel panelInicio = new JPanel(new BorderLayout());
        JLabel titulo = new JLabel("🎈 CAPTURA LOS GLOBOS", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 40));

        JButton btnIniciar = new JButton("Iniciar Juego");
        btnIniciar.setFont(new Font("Arial", Font.BOLD, 25));
        btnIniciar.addActionListener(e -> mostrarJuego());

        panelInicio.add(titulo, BorderLayout.CENTER);
        panelInicio.add(btnIniciar, BorderLayout.SOUTH);

        // Panel de juego (info + juego)
        JPanel panelJuegoCompleto = new JPanel(new BorderLayout());
        panelJuegoCompleto.add(new PanelInfo(), BorderLayout.NORTH);
        panelJuegoCompleto.add(new PanelJuego(this), BorderLayout.CENTER);

        contenedor.add(panelInicio, "inicio");
        contenedor.add(panelJuegoCompleto, "juego");
        contenedor.add(new PantallaFinal(this), "final");

        add(contenedor);
        mostrarInicio();
        setVisible(true);
    }

    public void mostrarInicio() {
        cardLayout.show(contenedor, "inicio");
    }

    public void mostrarJuego() {
        cardLayout.show(contenedor, "juego");
    }

    public void mostrarFinal() {
        cardLayout.show(contenedor, "final");
    }
}

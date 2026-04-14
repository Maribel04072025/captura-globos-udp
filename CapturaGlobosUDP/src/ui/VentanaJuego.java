/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import game.GameManager;
import model.Globo;
import model.Jugador;

import javax.swing.*;
import java.awt.*;

public class VentanaJuego extends JFrame {

    private JPanel zonaJuego;
    private BarraInfo barraInfo;
    private Image fondo;

    public VentanaJuego(String nombreJugador) {

        GameManager manager = GameManager.getInstance();

        setTitle("Captura los Globos");
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        fondo = new ImageIcon(
                getClass().getResource("/recursos/fondo.jpg")
        ).getImage();

        barraInfo = new BarraInfo();
        add(barraInfo, BorderLayout.NORTH);

        zonaJuego = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);

                for (Globo globo : manager.getEstado().getGlobos()) {
                    globo.dibujar(g);
                }
            }
        };

        add(zonaJuego, BorderLayout.CENTER);

        manager.setZonaJuego(zonaJuego);

        zonaJuego.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                manager.procesarClick(e.getX(), e.getY());
            }
        });

        manager.iniciarJuego(
                new Jugador(nombreJugador),
                new Jugador("CPU")
        );

        new Timer(30, e -> {
            int ancho = zonaJuego.getWidth();
            int alto = zonaJuego.getHeight();

            manager.getEstado().actualizarGlobos(ancho, alto);
            barraInfo.actualizar();
            zonaJuego.repaint();

        }).start();

        new Timer(1000, e -> {
            manager.getEstado().bajarTiempo();
            barraInfo.actualizar();
        }).start();

        setVisible(true);
    }
}

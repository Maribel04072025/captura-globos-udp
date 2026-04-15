/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import game.GameManager;
import model.Globo;
import model.Jugador;
import model.RecordManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseMotionAdapter;

/**
 * Ventana principal del juego donde ocurre toda la jugabilidad.
 *
 * Controla:
 * - Movimiento de globos
 * - Interaccion del mouse
 * - Temporizador del juego
 * - Finalizacion del juego
 *
 * Autor: Maribel Ceballos
 */
public class VentanaJuego extends JFrame {

    private JPanel zonaJuego;
    private BarraInfo barraInfo;
    private Image fondo;
    private Image mira;

    private int mouseX = 0;
    private int mouseY = 0;

    private Timer timerMovimiento;
    private Timer timerTiempo;

    public VentanaJuego(String nombreJugador) {

        GameManager manager = GameManager.getInstance();

        setTitle("Captura los Globos");
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        fondo = new ImageIcon(getClass().getResource("/recursos/fondo.jpg")).getImage();
        mira = new ImageIcon(getClass().getResource("/recursos/mira.png")).getImage();

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

                int size = 90;
                g.drawImage(mira, mouseX - size / 2, mouseY - size / 2, size, size, this);
            }
        };

        add(zonaJuego, BorderLayout.CENTER);
        manager.setZonaJuego(zonaJuego);

        /**
         * Seguimiento constante del mouse
         */
        zonaJuego.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(java.awt.event.MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
                zonaJuego.repaint();
            }
        });

        /**
         * Click del usuario para reventar globos
         */
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

        /**
         * Timer de movimiento de globos
         */
        timerMovimiento = new Timer(30, e -> {

            int ancho = zonaJuego.getWidth();
            int alto = zonaJuego.getHeight();

            manager.getEstado().actualizarGlobos(ancho, alto);
            barraInfo.actualizar();
            zonaJuego.repaint();
        });
        timerMovimiento.start();

        /**
         * Timer del tiempo de juego
         */
        timerTiempo = new Timer(1000, e -> {

            manager.getEstado().bajarTiempo();
            barraInfo.actualizar();

            int tiempo = manager.getEstado().getTiempoRestante();

            if (tiempo <= 0) {

                timerTiempo.stop();
                timerMovimiento.stop();

                terminarJuego(nombreJugador);
            }
        });

        timerTiempo.start();

        setVisible(true);
    }

    /**
     * Finaliza el juego y guarda el record
     */
    private void terminarJuego(String nombreJugador) {

        Jugador jugadorFinal = GameManager.getInstance()
                .getEstado()
                .getJugadorLocal();

        // Guardar record
        RecordManager.saveRecord(jugadorFinal.getPuntos());

        dispose();

        new PantallaFinal(
                jugadorFinal.getNombre(),
                jugadorFinal.getPuntos()
        );
    }
}
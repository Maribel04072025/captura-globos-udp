/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

/**
 * Autor: Maribel Ceballos
 *
 * Pantalla final del juego.
 * Muestra el resultado del jugador al terminar la partida,
 * incluyendo:
 * - Nombre del jugador
 * - Puntaje obtenido
 * - Record actual
 * - Mensaje segun si se supero el record
 * - Sonido de fin de juego o record
 *
 * Permite reiniciar el juego o salir del sistema.
 */

import game.GameManager;
import model.RecordManager;
import sound.SoundManager;

import javax.swing.*;
import java.awt.*;

public class PantallaFinal extends JFrame {

    private Image fondo;

    /**
     * Constructor de la pantalla final.
     *
     * @param nombre nombre del jugador
     * @param puntos puntaje obtenido en la partida
     */
    public PantallaFinal(String nombre, int puntos) {

        setTitle("Fin del Juego");
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        fondo = new ImageIcon(
                getClass().getResource("/recursos/fondo.jpg")
        ).getImage();

        PanelFondo panel = new PanelFondo();
        panel.setLayout(new GridBagLayout());

        PanelRedondeado caja = new PanelRedondeado(new Color(15, 35, 70, 220), 45);
        caja.setPreferredSize(new Dimension(520, 360));
        caja.setLayout(new BoxLayout(caja, BoxLayout.Y_AXIS));
        caja.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel titulo = new JLabel("¡TIEMPO TERMINADO!");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblNombre = new JLabel("Jugador: " + nombre);
        lblNombre.setFont(new Font("SansSerif", Font.PLAIN, 20));
        lblNombre.setForeground(new Color(210, 230, 255));
        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblPuntos = new JLabel("Puntos: " + puntos);
        lblPuntos.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblPuntos.setForeground(Color.WHITE);
        lblPuntos.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Record
        int record = RecordManager.getRecord();

        JLabel lblRecord = new JLabel("Récord: " + record);
        lblRecord.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblRecord.setForeground(Color.WHITE);
        lblRecord.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblMensaje = new JLabel();

        boolean nuevoRecord = puntos > record;

        if (nuevoRecord) {
            lblMensaje.setText("¡NUEVO RÉCORD!");
            lblMensaje.setForeground(Color.GREEN);

            SoundManager.getInstance().musicaGameOver();
        } else {
            lblMensaje.setText("Intenta superar el récord");
            lblMensaje.setForeground(Color.LIGHT_GRAY);

            SoundManager.getInstance().musicaGameOver();
        }

        lblMensaje.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblMensaje.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnReiniciar = crearBoton("Volver a jugar");
        JButton btnSalir = crearBoton("Salir");

        btnReiniciar.addActionListener(e -> {

            GameManager.getInstance().reiniciarJuego();

            dispose();

            new PantallaInicio();
        });

        btnSalir.addActionListener(e -> System.exit(0));

        caja.add(titulo);
        caja.add(Box.createVerticalStrut(25));
        caja.add(lblNombre);
        caja.add(Box.createVerticalStrut(10));
        caja.add(lblPuntos);
        caja.add(Box.createVerticalStrut(10));

        caja.add(lblRecord);
        caja.add(Box.createVerticalStrut(10));
        caja.add(lblMensaje);

        caja.add(Box.createVerticalStrut(25));
        caja.add(btnReiniciar);
        caja.add(Box.createVerticalStrut(12));
        caja.add(btnSalir);

        panel.add(caja);
        add(panel);

        setVisible(true);
    }

    /**
     * Crea un boton estilizado.
     */
    private JButton crearBoton(String texto) {
        JButton b = new JButton(texto) {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(new Color(70, 130, 180));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                super.paintComponent(g2);
                g2.dispose();
            }
        };

        b.setContentAreaFilled(false);
        b.setFocusPainted(false);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("SansSerif", Font.BOLD, 15));
        b.setMaximumSize(new Dimension(200, 40));
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.setBorder(BorderFactory.createEmptyBorder());
        return b;
    }

    /**
     * Panel de fondo con imagen.
     */
    class PanelFondo extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
    }

    /**
     * Panel con bordes redondeados.
     */
    class PanelRedondeado extends JPanel {
        private Color color;
        private int radio;

        public PanelRedondeado(Color color, int radio) {
            this.color = color;
            this.radio = radio;
            setOpaque(false);
        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(color);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radio, radio);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}
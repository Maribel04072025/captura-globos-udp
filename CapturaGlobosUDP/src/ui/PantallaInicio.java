/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import sound.SoundManager;

import javax.swing.*;
import java.awt.*;

/**
 * Pantalla de inicio del juego.
 * Permite ingresar el nombre del jugador e iniciar la partida.
 *
 * Autor: Maribel Ceballos
 */
public class PantallaInicio extends JFrame {

    private JTextField campoNombre;
    private Image fondo;

    public PantallaInicio() {

        setTitle("Captura los Globos");
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        fondo = new ImageIcon(
                getClass().getResource("/recursos/fondo.jpg")
        ).getImage();

        PanelFondo panel = new PanelFondo();
        panel.setLayout(new GridBagLayout());

        PanelRedondeado caja = new PanelRedondeado(new Color(15, 35, 70, 210), 45);
        caja.setPreferredSize(new Dimension(520, 360));
        caja.setLayout(new BoxLayout(caja, BoxLayout.Y_AXIS));
        caja.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel titulo = new JLabel("CAPTURA LOS GLOBOS");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitulo = new JLabel("Apunta a los globos y acumula puntos");
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 17));
        subtitulo.setForeground(new Color(210, 230, 255));
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        campoNombre = new JTextField();
        campoNombre.setMaximumSize(new Dimension(50, 20));
        campoNombre.setFont(new Font("SansSerif", Font.BOLD, 16));
        campoNombre.setHorizontalAlignment(JTextField.CENTER);
        campoNombre.setBorder(BorderFactory.createTitledBorder("Tu nombre"));
        campoNombre.setOpaque(false);
        campoNombre.setForeground(Color.BLACK);

        PanelRedondeado campoWrapper = new PanelRedondeado(Color.WHITE, 30);
        campoWrapper.setLayout(new BorderLayout());
        campoWrapper.add(campoNombre);
        campoWrapper.setMaximumSize(new Dimension(180, 40));

        JButton btnIniciar = crearBoton("Iniciar");
        JButton btnSalir = crearBoton("Salir");

        btnIniciar.addActionListener(e -> iniciarJuego());
        btnSalir.addActionListener(e -> System.exit(0));

        caja.add(titulo);
        caja.add(Box.createVerticalStrut(10));
        caja.add(subtitulo);
        caja.add(Box.createVerticalStrut(30));
        caja.add(campoWrapper);
        caja.add(Box.createVerticalStrut(25));
        caja.add(btnIniciar);
        caja.add(Box.createVerticalStrut(12));
        caja.add(btnSalir);

        panel.add(caja);
        add(panel);

        // Reproduce musica de fondo del menu
        SoundManager.getInstance().musicaFondo();

        setVisible(true);
    }

    /**
     * Crea un boton con estilo personalizado.
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
        b.setMaximumSize(new Dimension(170, 38));
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.setBorder(BorderFactory.createEmptyBorder());
        return b;
    }

    /**
     * Inicia el juego validando el nombre del jugador.
     */
    private void iniciarJuego() {
        String nombre = campoNombre.getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Escribe tu nombre");
            return;
        }

        dispose();
        new VentanaJuego(nombre);
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
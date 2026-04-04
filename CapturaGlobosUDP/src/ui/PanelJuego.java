/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import game.GameManager;
import model.Globo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelJuego extends JPanel {

    private GameManager manager;

    public PanelJuego() {
        manager = GameManager.getInstance();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                manager.procesarClick(e.getX(), e.getY());
                repaint();
            }
        });

        // Repintado constante
        Timer timer = new Timer(30, e -> repaint());
        timer.start();
    }

@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g;

    // 🎨 Fondo cielo degradado elegante
    GradientPaint cielo = new GradientPaint(
            0, 0, new Color(224, 247, 255),
            0, getHeight(), new Color(200, 230, 255)
    );

    g2.setPaint(cielo);
    g2.fillRect(0, 0, getWidth(), getHeight());

    // 🎈 Dibujar globos
    for (Globo globo : manager.getEstado().getGlobos()) {
        g2.setColor(globo.getColor());
        g2.fillOval(
                globo.getX() - globo.getRadio(),
                globo.getY() - globo.getRadio(),
                globo.getRadio() * 2,
                globo.getRadio() * 2
        );
    }
}
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import javax.sound.sampled.*;
import java.net.URL;

public class Sonido {

    private Clip clip;

    public Sonido(String ruta) {
        try {
            URL url = getClass().getResource(ruta);
            AudioInputStream audio = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audio);
        } catch (Exception e) {
            System.out.println("No se pudo cargar el sonido: " + ruta);
        }
    }

    public void reproducir() {
        if (clip == null) return;

        if (clip.isRunning()) {
            clip.stop();
        }
        clip.setFramePosition(0);
        clip.start();
    }

    public void loop() {
        if (clip == null) return;

        if (!clip.isRunning()) {
            clip.setFramePosition(0);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void detener() {
        if (clip != null) {
            clip.stop();
        }
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import javax.sound.sampled.*;
import java.net.URL;

public class Sonido {

    private String ruta;

    public Sonido(String ruta) {
        this.ruta = ruta;
    }

    public void reproducir() {
        try {
            URL url = getClass().getResource(ruta);

            AudioInputStream audio = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();

        } catch (Exception e) {
            System.out.println("No se pudo reproducir: " + ruta);
        }
    }

    public void loop() {
        try {
            URL url = getClass().getResource(ruta);

            AudioInputStream audio = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (Exception e) {
            System.out.println("No se pudo reproducir: " + ruta);
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sound;

import util.Sonido;

public class SoundManager {

    private static SoundManager instance;

    private Sonido musica;
    private Sonido pop;
    private Sonido gameOver; // 🔥 NUEVO

    private SoundManager() {
        musica = new Sonido("/recursos/SonidoJuego.wav");
        pop = new Sonido("/recursos/Globo.wav");
        gameOver = new Sonido("/recursos/FinalJuego.wav");
    }

    public static SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    public void musicaFondo() {
        musica.loop();
    }

    public void sonidoPop() {
        pop.reproducir();
    }

    // 🛑 detener música del juego
    public void detenerMusica() {
        if (musica != null) {
            musica.detener();
        }
    }

    // 💀 NUEVO: música fin del juego
    public void musicaGameOver() {

        detenerMusica(); // primero parar la del juego

        if (gameOver != null) {
            gameOver.reproducir();
        }
    }
}
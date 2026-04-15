/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sound;

/**
 * Autor: Maribel Ceballos
 *
 * Clase encargada de gestionar todos los sonidos del juego.
 * Implementa el patron Singleton para garantizar una unica instancia.
 *
 * Responsabilidades:
 * - Reproducir musica de fondo
 * - Reproducir efectos de sonido (pop de globos)
 * - Reproducir musica de fin de juego
 * - Detener la musica cuando sea necesario
 */

import util.Sonido;

public class SoundManager {

    private static SoundManager instance;

    private Sonido musica;
    private Sonido pop;
    private Sonido gameOver;

    /**
     * Inicializa todos los sonidos del juego.
     */
    private SoundManager() {
        musica = new Sonido("/recursos/SonidoJuego.wav");
        pop = new Sonido("/recursos/Globo.wav");
        gameOver = new Sonido("/recursos/FinalJuego.wav");
    }

    /**
     * Retorna la instancia unica del SoundManager.
     */
    public static SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    /**
     * Reproduce la musica de fondo en loop.
     */
    public void musicaFondo() {
        musica.loop();
    }

    /**
     * Reproduce el sonido cuando explota un globo.
     */
    public void sonidoPop() {
        pop.reproducir();
    }

    /**
     * Detiene la musica de fondo.
     */
    public void detenerMusica() {
        if (musica != null) {
            musica.detener();
        }
    }

    /**
     * Reproduce la musica de fin de juego.
     * Primero detiene la musica principal.
     */
    public void musicaGameOver() {

        detenerMusica();

        if (gameOver != null) {
            gameOver.reproducir();
        }
    }
}
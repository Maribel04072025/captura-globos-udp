/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Random;

public class RandomUtil {

    private static Random rand = new Random();

    public static int randomX(int ancho) {
        return rand.nextInt(ancho - 60) + 30;
    }

    public static int randomY(int alto) {
        return rand.nextInt(alto - 60) + 30;
    }
}
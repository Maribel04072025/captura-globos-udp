/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author marib
 */
import java.util.Random;

public class RandomUtil {

    private static Random rand = new Random();

    public static int randomX() {
        return rand.nextInt(600) + 50;
    }

    public static int randomY() {
        return rand.nextInt(400) + 50;
    }
}

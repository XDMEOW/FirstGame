package dev.vortexD.Utils.Listners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyL extends KeyAdapter {
    private static boolean[] isKeyPressed = new boolean[128];

    public static boolean isKeyPressed(int key) {
        return isKeyPressed[key];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() < 128) {
            isKeyPressed[e.getKeyCode()] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() < 128) {
            isKeyPressed[e.getKeyCode()] = false;
        }
    }
}

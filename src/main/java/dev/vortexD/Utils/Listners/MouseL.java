package dev.vortexD.Utils.Listners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseL extends MouseAdapter {
    public boolean isMousePressed = false;
    public boolean isMouseDragged = false;
    public float MouseX = 0;
    public float MouseY = 0;
    public float MouseDX = 0;
    public float MouseDY = 0;

    public int MouseButton = -1;

    @Override
    public void mousePressed(MouseEvent e) {
        this.isMousePressed = true;
        this.MouseButton = e.getButton();
        MouseX = e.getX();
        MouseY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.isMousePressed = false;
        this.isMouseDragged = false;
        this.MouseDX = 0;
        this.MouseDY = 0;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.MouseX = e.getX();
        this.MouseY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.isMouseDragged = true;
        this.MouseDX = e.getX() - MouseX;
        this.MouseDY = e.getY() - MouseY;
        this.MouseX = e.getX();
        this.MouseY = e.getY();
    }
}

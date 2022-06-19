package dev.vortexD.Game.Panels;

import dev.vortexD.Game.Helper.ParallaxBG;
import dev.vortexD.Utils.Listners.KeyL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {
    private static GamePanel instance = null;

    //Panel Configuration
    public static final int PANEL_WIDTH = 1920;
    public static final int PANEL_HEIGHT = 1080;
    ParallaxBG parallaxBG = new ParallaxBG();

    double lastFrameTime = 0.0;
    Timer GameTimer = new Timer(1000/60, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            lastFrameTime = System.nanoTime();
            updateALL();
            double fps = 1000000000.0 / (System.nanoTime() - lastFrameTime);
            System.out.println("FPS: " + fps);
            //exit if escape is pressed
            if (KeyL.isKeyPressed(KeyEvent.VK_ESCAPE)) {
                System.exit(0);
            }
        }
    });
    private GamePanel() {

            super();
            setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
            setBackground(new Color(34, 34, 35));
            setVisible(true);
            GameTimer.start();
            parallaxBG.startRendering();
    }

    public void updateALL() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(parallaxBG.getFrame(), 0, 0, null);
    }
    public static GamePanel getInstance() {
        if (instance == null) {
            instance = new GamePanel();
        }
        return instance;
    }
}


package dev.vortexD.Game;

import dev.vortexD.Game.Panels.GamePanel;
import dev.vortexD.Utils.Listners.KeyL;
import dev.vortexD.Utils.Listners.MouseL;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame implements Runnable {
    private static GameWindow instance = null;
    public Thread GameWindowThread;
    public MouseL MouseListner = new MouseL();
    public KeyL KeyListner = new KeyL();
    private boolean isGameRunning = false;
    private GameWindow() {

        super("First Game | VortexD");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(GamePanel.getInstance(), BorderLayout.CENTER);
        setSize(GamePanel.PANEL_WIDTH, GamePanel.PANEL_HEIGHT);
        addMouseListener(MouseListner);
        addMouseMotionListener(MouseListner);
        addKeyListener(KeyListner);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void init() {
        GameWindowThread = new Thread(this);
        GameWindowThread.start();
        isGameRunning = true;
    }

    public static GameWindow getInstance() {
        if (instance == null) {
            instance = new GameWindow();
        }
        return instance;
    }

    @Override
    public void run() {
        try{
            while (isGameRunning) {
                Thread.sleep(1000);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

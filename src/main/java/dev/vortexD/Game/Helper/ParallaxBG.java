package dev.vortexD.Game.Helper;

import dev.vortexD.Game.Panels.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;


public class ParallaxBG {
    Image layers[];

    final int WIDTH = GamePanel.PANEL_WIDTH;
    final int HEIGHT = GamePanel.PANEL_HEIGHT;

    public final int PlayerSpeed = 5;
    double layerSpeedFactor[] = new double[5];

    int currentLayerX[] = new int[5];

    int PreRenderFrames = 10;

    Queue<BufferedImage> preRenderedImages = new LinkedList<BufferedImage>();
    Thread renderBG;

    public ParallaxBG() {
        layers = new Image[5];

        //First Layer
        layers[0] = new ImageIcon("resources/Background/sky.png").getImage();
        layerSpeedFactor[0] = 1;
        currentLayerX[0] = 0;
        //Second Layer
        layers[1] = new ImageIcon("resources/Background/clouds_1.png").getImage();
        layerSpeedFactor[1] = 1.2;
        currentLayerX[1] = 0;
        //Third Layer
        layers[2] = new ImageIcon("resources/Background/clouds_2.png").getImage();
        layerSpeedFactor[2] = 1.4;
        currentLayerX[2] = 0;
        //Fourth Layer
        layers[3] = new ImageIcon("resources/Background/rocks.png").getImage();
        layerSpeedFactor[3] = 1.8;
        currentLayerX[3] = 0;
        //Fifth Layer
        layers[4] = new ImageIcon("resources/Background/ground.png").getImage();
        layerSpeedFactor[4] = 2.5;
        currentLayerX[4] = 0;

        //fit image to panel
        for (int i = 0; i < layers.length; i++) {
            layers[i] = layers[i].getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT);
        }
    }

    private void createFrame(Graphics2D g2d){
        for(int i = 0; i < layers.length; i++) {

            g2d.drawImage(layers[i], currentLayerX[i], 0, null);

            //duplicates the layer and draw it on the other side of the screen to avoid black bars
            g2d.drawImage(layers[i], currentLayerX[i] + WIDTH, 0, null);

            currentLayerX[i] -= (int) (layerSpeedFactor[i] * PlayerSpeed);

            if(currentLayerX[i] < -WIDTH) {

                currentLayerX[i] = 0;

            }
        }
    }

    public void startRendering() {
        renderBG = new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
                while (true) {
                    if(preRenderedImages.size() < PreRenderFrames) {
                        Graphics2D g2d = image.createGraphics();
                        createFrame(g2d);
                        preRenderedImages.add(image);
                    }
                }
            }
        });
        renderBG.start();
    }

    private BufferedImage createPrerenderedImage() {
        BufferedImage prerenderedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = prerenderedImage.createGraphics();
        createFrame(g2d);
        g2d.dispose();
        return prerenderedImage;
    }

    public BufferedImage getFrame(){
        if(preRenderedImages.size() > 0) {
            return preRenderedImages.poll();
        }
        return createPrerenderedImage();
    }
}

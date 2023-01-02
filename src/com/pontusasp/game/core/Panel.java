package com.pontusasp.game.core;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Pontus on 2018-04-29.
 */
public class Panel extends JPanel {

    private BufferedImage graphicsBuffer;
    private Graphics graphics;
    private Color clearColor = Color.BLACK;

    private ArrayList<Drawable> drawables = new ArrayList<>();

    public static int width, height;

    public Panel(int width, int height) {
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        graphicsBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        graphics = graphicsBuffer.getGraphics();
    }

    public void setClearColor(Color color) {
        clearColor = color;
    }

    public void addDrawable(Drawable drawable) {
        drawables.add(drawable);
    }

    public void removeDrawable(Drawable drawable) {
        drawables.remove(drawable);
    }

    public void removeDrawable(int index) {
        drawables.remove(index);
    }

    public BufferedImage getGraphicsBuffer() {
        return graphicsBuffer;
    }

    @Override
    public void paintComponent(Graphics g) {
        graphics.setColor(clearColor);
        graphics.fillRect(0, 0, width, height);
        for (int i = 0, drawablesSize = drawables.size(); i < drawablesSize; i++) {
            Drawable drawable = drawables.get(i);
            drawable.draw(graphics);
        }
        g.drawImage(graphicsBuffer, 0, 0, null);
        repaint();
    }

}

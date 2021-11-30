package com.pontusasp.game.shapes;

import java.awt.*;

/**
 * Created by Pontus on 2018-04-29.
 */
public class Line extends Shape {

    protected float x2, y2;
    public boolean ruler = false;
    public Color fontColor = Color.WHITE;
    public float rulerScaleX = 1;
    public float rulerScaleY = 1;

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawLine(Math.round(x), Math.round(y),
                Math.round(x2), Math.round(y2));
        if (ruler) {
            float dx = Math.min(x, x2) + Math.abs(x - x2) / 2f;
            float dy = Math.min(y, y2) + Math.abs(y - y2) / 2f;
            g.setColor(fontColor);
            g.drawString(String.format("%.0f", getMagnitude()), (int) dx, (int) dy);
        }
    }

    public void setOrigin(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setEndPoint(int x, int y) {
        this.x2 = x;
        this.y2 = y;
    }

    public float getMagnitude() {
        return (float) Math.sqrt(Math.pow((x - x2) * rulerScaleX, 2) + Math.pow((y - y2) * rulerScaleY, 2));
    }

    @Override
    public void move(float offsetX, float offsetY) {
        super.move(offsetX, offsetY);
        x2 += offsetX;
        y2 += offsetY;
    }

}

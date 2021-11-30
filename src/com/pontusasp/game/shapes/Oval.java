package com.pontusasp.game.shapes;

import java.awt.*;

public class Oval extends Shape {

    public int width, height;
    public boolean hollow = false;

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        if(hollow) g.drawOval(Math.round(x), Math.round(y), width, height);
        else g.fillOval(Math.round(x), Math.round(y), width, height);
    }
}

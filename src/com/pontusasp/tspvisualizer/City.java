package com.pontusasp.tspvisualizer;

import com.pontusasp.game.core.Drawable;
import com.pontusasp.game.core.Dynamic;
import com.pontusasp.game.shapes.Line;
import com.pontusasp.game.shapes.Oval;
import com.pontusasp.game.shapes.Rectangle;

import java.awt.*;

public class City implements Drawable, Dynamic {

    private Oval rect;
    private boolean visited = false;
    public final int id;
    private int visitedInOrder = -1;
    public final float X, Y;
    private City connectedCity = null;
    private final Line road = new Line();

    public City(int id, float x, float y, int total) {
        this.id = id;
        int SIZE = 24;
        this.X = x - (SIZE / 2f) / TspVisualizer.WIDTH;
        this.Y = y - (SIZE / 2f) / TspVisualizer.HEIGHT;
        rect = new Oval();
        rect.setColor(new Color(0, 200 - (int) ((1 - (float) id / total) * 200), 255 - (int) ((float) id / total * 255)));
        rect.width = rect.height = SIZE;
        rect.hollow = true;
        rect.setPosition(convertPointX(x), convertPointY(y));
        road.setOrigin(cityCenterX(), cityCenterY());
        road.setColor(Color.ORANGE);
        //road.ruler = true;
        road.rulerScaleX = 100f/TspVisualizer.WIDTH;
        road.rulerScaleY = 100f/TspVisualizer.HEIGHT;
    }

    public void connectCity(City city) {
        connectedCity = city;
        road.setEndPoint(city.cityCenterX(), city.cityCenterY());
    }

    private static int visitOrder = 0;
    public void visit() {
        visited = true;
        visitedInOrder = visitOrder++;
    }

    public int cityCenterX() {
        return Math.round(convertPointX(X) + rect.width / 2f);
    }

    public int cityCenterY() {
        return Math.round(convertPointY(Y) + rect.height / 2f);
    }

    private float convertPointX(float x) {
        return rect.width / 2f + x / 100 * (TspVisualizer.WIDTH - rect.width);
    }

    private float convertPointY(float y) {
        return rect.height / 2f + y / 100 * (TspVisualizer.HEIGHT - rect.height);
    }

    @Override
    public void draw(Graphics g) {
        rect.draw(g);
        g.setColor(Color.WHITE);
        //g.drawString("" + id, Math.round(convertPointX(X) + 22), Math.round(convertPointY(Y) + 28));
       // if (visited)
        //    g.drawString("(" + visitedInOrder + ")", Math.round(convertPointX(X) + 18), Math.round(convertPointY(Y) + 12));
        if (connectedCity != null) {
            road.draw(g);
        }
    }

    @Override
    public void update(float delta) {
        rect.hollow = !visited;
    }
}

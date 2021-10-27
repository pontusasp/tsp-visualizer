package com.pontusasp.tspvisualizer;

import com.pontusasp.game.core.Panel;
import com.pontusasp.game.core.Updater;
import com.pontusasp.game.core.Window;

import java.util.ArrayList;
import java.util.Scanner;

public class TspVisualizer {

    private Scanner sc = new Scanner(System.in);
    public final static int WIDTH = 800;
    public final static int HEIGHT = 800;

    private final ArrayList<City> cities = new ArrayList<>();

    private Updater update;
    private Panel panel;
    private Window window;

    public static int N;

    public TspVisualizer() {
        update = new Updater();
        panel = new Panel(800, 800);

        getNodes();

        window = new Window("TSP Visualizer", panel);
        update.start();

        int[] path = getPath();
        walkPath(path);
    }

    public void walkPath(int[] path) {
        for (int i = 0; i < N; i++) {
            City city = cities.get(path[i]);
            City nextCity = cities.get(path[(i + 1) % N]);
            city.visit();
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            city.connectCity(nextCity);
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int[] getPath() {
        int[] path = new int[N];
        int[] order = new int[N];
        for (int i = 0; i < N; i++) {
            order[i] = sc.nextInt();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (order[j] == i) {
                    path[i] = j;
                    break;
                }
            }
        }

        return path;
    }

    public void generateNodes() {
        for (int i = 0; i < N; i++) {
            float x = (float) Math.random() * 100;
            float y = (float) Math.random() * 100;
            System.out.printf("%.4f %.4f\n", x, y);
            City city = new City(i, x, y, N);
            cities.add(city);
            panel.addDrawable(city);
            update.addToQueue(city);
        }
    }

    public void getNodes() {
        N = sc.nextInt();
        if (N == 0) {
            N = sc.nextInt();
            generateNodes();
            return;
        }
        for (int i = 0; i < N; i++) {
            float x = sc.nextFloat();
            float y = sc.nextFloat();
            City city = new City(i, x, y, N);
            cities.add(city);
            panel.addDrawable(city);
            update.addToQueue(city);
        }
    }

    public static void main(String[] args) {
        new TspVisualizer();
    }

}

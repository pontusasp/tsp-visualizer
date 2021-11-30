package com.pontusasp.tspvisualizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Sorter {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        ArrayList<Integer> ints = new ArrayList<>();
        while ((n = sc.nextInt()) != -1) {
            ints.add(n);
        }

        Collections.sort(ints);
        for(int i : ints)
            System.out.println(i);
    }

}

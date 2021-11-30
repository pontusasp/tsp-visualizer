package com.pontusasp.tspvisualizer;

import com.pontusasp.game.core.Panel;
import com.pontusasp.game.core.Updater;
import com.pontusasp.game.core.Window;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Scanner;

public class TspVisualizer extends Panel implements MouseListener {

    private Scanner sc = new Scanner(System.in);
    public final static int WIDTH = 800;
    public final static int HEIGHT = 800;

    private final ArrayList<City> cities = new ArrayList<>();

    private Updater update;
    private Panel panel;
    private Window window;

    public static int N;

    public TspVisualizer() {
        super(WIDTH, HEIGHT);
        panel = this;
        update = new Updater();

        getNodes();

        addMouseListener(this);
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
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            city.connectCity(nextCity);
            try {
                Thread.sleep(25);
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

        return order;
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

    @Override
    public void mouseClicked(MouseEvent e) {
        if (N != 0) return;
        float x = (float) e.getX() / WIDTH * 100;
        float y = (float) e.getY() / HEIGHT * 100;
        System.out.printf("%.4f %.4f\n", x, y);
        City city = new City(cities.size(), x, y, cities.size());
        cities.add(city);
        panel.addDrawable(city);
        update.addToQueue(city);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

/*

3-opt

200
48.2288 20.3993
19.6558 19.9549
92.8581 91.4531
53.1551 56.5540
63.4428 88.4201
83.8463 85.8673
46.2409 33.0563
26.2271 81.7547
54.2587 35.6710
78.4238 1.4547
79.2786 16.7583
89.0956 54.3831
57.4606 4.1754
57.4217 36.2618
43.0277 74.7197
53.3077 48.2610
39.3320 11.3946
51.2965 37.3369
19.5032 3.2251
56.1394 13.5813
79.9236 23.1708
60.5844 23.1479
8.9277 81.5040
16.6568 81.2038
20.4126 66.9602
20.5595 98.3549
97.4556 56.0352
8.2883 66.1262
14.5698 91.9947
34.1604 2.3282
58.8875 9.4773
57.6715 95.5228
50.3504 8.0157
95.4495 29.3040
68.8903 58.3646
4.5417 11.7392
32.6234 59.2176
50.8721 83.0676
35.2399 48.7095
82.4277 25.6364
43.0554 15.0442
70.5798 54.0540
72.6008 43.6523
4.9195 20.6718
18.1648 84.4769
27.0842 31.7587
75.3584 22.2986
67.2744 33.1110
11.5620 85.4565
3.5070 90.3467
67.0203 33.8848
98.1268 58.7577
31.3902 26.0780
78.6909 10.3524
29.7019 76.4132
21.0678 61.5956
68.9844 79.2002
61.4383 24.3599
42.2113 93.8958
10.8586 84.7299
59.7620 57.1101
71.1428 99.5545
80.2506 11.3497
54.0465 89.0219
42.3625 17.8831
87.0789 95.0366
57.2470 51.6817
48.7451 69.8043
7.0503 60.0786
13.6158 56.7876
9.3322 3.6061
3.3476 27.0708
49.3980 87.4136
33.6208 38.1796
52.9827 34.7332
18.9376 89.5281
70.2641 50.0525
47.3188 58.9187
68.8390 68.9711
65.1625 17.9032
88.0819 94.1559
18.8716 40.3124
90.2181 72.3814
84.3358 37.8943
54.4745 76.3275
77.9442 79.7527
99.8352 16.6280
83.5342 78.1579
89.2120 24.6008
48.5241 52.3046
63.2502 14.1610
99.4544 13.7217
75.5267 52.6051
2.2569 52.2911
52.5551 44.2757
45.7952 40.2140
81.8010 58.5527
58.5669 12.7645
38.8168 14.1878
77.9316 29.3006
0.6446 65.6869
78.8254 65.7566
56.7922 24.2643
34.6452 40.7194
21.3760 85.8317
26.8349 31.1957
70.4305 31.5526
41.6772 91.3039
76.9244 12.6845
92.8927 47.3833
92.6782 30.7464
86.8702 6.3852
31.1674 76.4913
3.7011 80.9204
38.1033 96.7305
1.6622 3.9642
97.7198 16.7457
65.3885 78.9727
11.8532 57.8444
8.7021 87.0447
6.1897 54.5523
11.9087 51.7681
63.4625 94.0057
20.3482 44.5161
56.9174 71.2953
56.0815 98.2145
59.0265 78.8333
97.8188 85.9795
30.2121 25.2518
65.5438 12.5297
50.2560 9.9122
5.7582 60.9044
41.1030 62.7925
10.8724 34.7742
43.9279 96.3390
21.9850 37.5098
55.6198 5.8079
58.1488 33.1772
0.2494 97.6900
87.1875 41.7578
9.6181 22.0030
3.2276 31.1457
13.6492 33.5521
4.3331 81.8516
96.1662 9.6999
69.9767 16.8494
54.5130 7.7566
22.5552 8.6285
12.5075 53.7879
19.7537 98.2693
75.3774 16.4578
65.2858 87.2382
82.6157 34.4648
44.3852 91.6029
10.0997 34.1949
87.3969 80.4969
96.3571 95.4067
39.9126 55.9157
19.5173 97.0810
71.1185 69.3085
31.2768 3.0087
83.3726 11.9290
62.1136 27.2559
95.4974 60.5424
48.0229 53.3373
59.8765 7.8359
93.2056 67.7472
39.1626 57.7602
75.4264 8.4340
12.2534 20.0355
21.1689 21.8773
1.1642 95.1798
26.2499 68.6112
10.5248 1.6758
74.2152 16.3589
49.6491 60.2693
9.6238 12.3823
63.4746 16.8956
56.2396 32.2183
33.1418 55.3607
31.1822 60.6637
19.5805 23.6574
95.2504 72.6879
14.1934 73.6740
96.9167 46.8930
29.5733 93.0604
12.9003 62.2089
10.1665 90.1416
61.7632 99.7006
67.8880 78.0250
68.6323 95.6829
50.9203 92.4257
88.9362 69.7776
35.2743 69.2391
56.9567 82.1160
88.1619 2.5166
11.0273 57.5800
89.8306 1.2574
61.3752 51.7928
16.2298 64.3048
21
102
178
137
13
8
74
17
95
6
0
64
40
98
16
29
160
147
18
173
70
115
35
176
169
140
43
71
141
154
133
142
181
1
170
128
52
105
45
135
81
123
121
120
93
68
131
27
183
23
44
104
7
107
153
58
134
114
185
25
149
158
75
28
187
138
171
49
119
48
59
22
143
113
100
196
148
69
118
186
199
55
24
172
54
112
193
14
67
124
84
151
4
61
190
122
188
125
31
191
63
72
37
194
126
117
189
56
85
5
155
87
192
82
182
166
163
51
26
184
109
139
83
152
46
108
97
19
130
32
146
136
12
165
30
129
90
177
79
145
174
150
10
161
62
53
168
9
197
195
111
144
91
116
86
33
110
88
39
20
99
106
47
50
42
92
76
41
34
60
198
66
3
175
77
132
157
167
180
36
179
38
103
73
164
89
15
94
78
159
101
96
11
127
2
80
65
156
162
57


 */
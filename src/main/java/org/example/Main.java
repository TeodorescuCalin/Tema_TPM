package org.example;

import java.util.ArrayList;

import static java.util.Collections.max;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int n = 4;
        MyLock lock = new MyLock(n);
        int[] v = new int[n + 1];
        v[0] = 10000;
        ArrayList<MyThread> t = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            t.add(new MyThread(lock, i, v));
        }

        for (int i = 0; i < n; i++) {
            t.get(i).start();
        }

        for (int i = 0; i < n; i++) {
            t.get(i).join();
        }

        for (int i = 0; i <= n; i++) {
            System.out.println(v[i]);
        }
    }

}
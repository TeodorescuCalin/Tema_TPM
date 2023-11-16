package org.example;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.max;

public class MyLock {
    int n;
    List<Integer> label = new ArrayList<>();
    List<Boolean> access = new ArrayList<>();
    List<Boolean> flag = new ArrayList<>();

    public MyLock(int n) {
        this.n = n;
        for (int k = 0; k < n; k++) {
            flag.add(k, false);
            access.add(k, false);
            label.add(k, k + 1);
        }
    }

    public boolean have_priority(int i) {
        for (int j = 0; j < n; j++) {
            if (j != i && (flag.get(j) && label.get(j) < label.get(i)))
                return false;
        }
        return true;
    }

    public boolean access_true(int i) {
        for (int j = 0; j < n; j++)
            if (access.get(j) && i != j)
                return true;
        return false;
    }

    public void lock(int i) {
        flag.set(i, true);
        do {
            access.set(i, false);
            while (!have_priority(i)) {}
            access.set(i,true);
        } while (access_true(i));
    }

    public void unlock(int i) {
        for (int j = 0; j < n; j++) {
            if (label.get(j) > label.get(i))
                label.set(j, label.get(j) - 1);
        }
        label.set(i, max(label) + 1);
        access.set(i, false);
        flag.set(i, false);
    }
}

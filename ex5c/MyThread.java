package org.example;

public class MyThread extends Thread {
    private MyLock lock;
    private int id;
    private int[] v;

    public MyThread(MyLock lock, int id, int[] v) {
        this.lock = lock;
        this.id = id;
        this.v = v;
    }

    @Override
    public void run() {
        while (v[0] > 0)
        {
            lock.lock(this.id);
            v[this.id + 1]++;
            v[0]--;
            lock.unlock(this.id);
        }
    }
}

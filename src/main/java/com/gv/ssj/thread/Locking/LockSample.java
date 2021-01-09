package com.gv.ssj.thread.Locking;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockSample {

    public static void main(String[] args) {
        final ThreadFactory threadFactory = new ThreadFactory();

        Thread first = new Thread(new Runnable() {
            public void run() {
                try {
                    threadFactory.firstMillenium();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread second = new Thread(new Runnable() {
            public void run() {
                try {
                    threadFactory.secondMillenium();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread third = new Thread(new Runnable() {
            public void run() {
                try {
                    threadFactory.thirdMillenium();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        first.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        second.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        third.start();
    }

}

class ThreadLockFactory {

    Lock lock = new ReentrantLock();
    Condition lockCondition = lock.newCondition();

    public void firstMillenium() throws InterruptedException {
        lock.lock();
        try {
            System.out.println("First Millenium started");
            lockCondition.await();
            System.out.println("First Millenium progresses after pause");
        } finally {
            lock.unlock();
        }


    }

    public void secondMillenium() throws InterruptedException {
        lock.lock();
        try {
            System.out.println("Second Millenium started");
            lockCondition.await();
            System.out.println("Second Millenium progresses after pause");
        } finally {
            lock.unlock();
        }
    }

    public void thirdMillenium() throws InterruptedException {
        lock.lock();
        try {
            System.out.println("Third millenium started");
            Thread.sleep(1000);
            lockCondition.signalAll();
            System.out.println("Third millenium finished");
        } finally {
            lock.unlock();
        }
    }
}

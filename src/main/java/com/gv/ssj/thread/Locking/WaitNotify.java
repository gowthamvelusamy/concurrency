package com.gv.ssj.thread.Locking;

public class WaitNotify {

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

class ThreadFactory {

    public void firstMillenium() throws InterruptedException {
        synchronized (this){
            System.out.println("First Millenium started");
            wait();
            System.out.println("First Millenium progresses after pause");
        }
    }

    public void secondMillenium() throws InterruptedException {
        synchronized (this){
            System.out.println("Second Millenium started");
            wait();
            System.out.println("Second Millenium progresses after pause");
        }
    }

    public void thirdMillenium() throws InterruptedException {
        synchronized (this){
            System.out.println("Third millenium started");
            Thread.sleep(1000);
            notifyAll();
            System.out.println("Third millenium finished");
        }
    }
}
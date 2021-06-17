package com.company;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * https://leetcode-cn.com/problems/fizz-buzz-multithreaded/submissions/
 * 1195. 交替打印字符串
 */
class FizzBuzz {
    private int n;

    private Semaphore number = new Semaphore(1);
    private Semaphore fizz = new Semaphore(0);
    private Semaphore buzz = new Semaphore(0);
    private Semaphore fizzbuzz = new Semaphore(0);
    private volatile boolean isRun = true;
    private Set<Thread> threadSet = new HashSet<>();

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        threadSet.add(Thread.currentThread());
        try {
            while (isRun) {
                fizz.acquire();
                printFizz.run();
                number.release();
            }
        } catch (InterruptedException ignored) {
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        threadSet.add(Thread.currentThread());
        try {
            while (isRun) {
                buzz.acquire();
                printBuzz.run();
                number.release();
            }
        } catch (InterruptedException ignore) {
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        threadSet.add(Thread.currentThread());
        try {
            while (isRun) {
                fizzbuzz.acquire();
                printFizzBuzz.run();
                number.release();
            }
        } catch (InterruptedException ignore) {

        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            number.acquire();
            if (i % 15 == 0) {
                fizzbuzz.release();
                continue;
            }
            if (i % 3 == 0) {
                fizz.release();
                continue;
            }
            if (i % 5 == 0) {
                buzz.release();
                continue;
            }
            printNumber.accept(i);
            number.release();
        }
        isRun = false;
        for (Thread thread : threadSet) {
            thread.interrupt();
        }
    }
}

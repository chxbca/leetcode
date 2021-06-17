package com.company;

import java.util.concurrent.Semaphore;

/**
 * https://leetcode-cn.com/problems/the-dining-philosophers/
 * 1226. 哲学家进餐
 */
class DiningPhilosophers {

    private final int ALL_COUNT = 5;

    private final Semaphore[] fork = new Semaphore[ALL_COUNT];

    public DiningPhilosophers() {
        fork[0] = new Semaphore(1);
        for (int i = 1; i < fork.length; i++) {
            fork[i] = new Semaphore(0);
        }
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        fork[philosopher].acquire();
        pickLeftFork.run();
        pickRightFork.run();
        eat.run();
        putLeftFork.run();
        putRightFork.run();
        fork[(philosopher + 1) % ALL_COUNT].release();
    }
}

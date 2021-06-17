package com.company;

/**
 * https://leetcode-cn.com/problems/the-dining-philosophers/
 * 1226. 哲学家进餐
 */
class DiningPhilosophers {

    public DiningPhilosophers() {
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        synchronized (DiningPhilosophers.class) {
            pickLeftFork.run();
            pickRightFork.run();
            eat.run();
            putLeftFork.run();
            putRightFork.run();
        }
    }
}

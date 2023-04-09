package deadlockslivelocks;

import java.util.concurrent.locks.ReentrantLock;

public class DeadlockExample{
    private final ReentrantLock lock1 = new ReentrantLock();
    private final ReentrantLock lock2 = new ReentrantLock();

    public void operation1() {
        lock1.lock();
        System.out.println("operation1: lock1 acquired");
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock2.lock();
        System.out.println("operation1: lock2 acquired");
        lock2.unlock();
        System.out.println("operation1: lock2 freed");
        lock1.unlock();
        System.out.println("operation1: lock1 freed");
    }

    public void operation2() {
        lock2.lock();
        System.out.println("operation2: lock2 acquired");
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock1.lock();
        System.out.println("operation2: lock1 acquired");
        lock1.unlock();
        System.out.println("operation1: lock1 freed");
        lock2.unlock();
        System.out.println("operation1: lock2 freed");

    }

}

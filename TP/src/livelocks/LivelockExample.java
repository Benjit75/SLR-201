package livelocks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LivelockExample {

    private final static ReentrantLock lock1 = new ReentrantLock();
    private final static ReentrantLock lock2 = new ReentrantLock();

    public final void operation1() {
        do {
            try {
                if (lock1.tryLock(50, TimeUnit.MILLISECONDS)) {
                    System.out.println("operation1: lock1 acquired");
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (lock2.tryLock(50, TimeUnit.MILLISECONDS)) {
                        System.out.println("operation1: lock2 acquired");
                        lock2.unlock();
                        lock1.unlock();
                        break;
                    } else {
                        System.out.println("operation1: lock2 not acquired");
                        lock1.unlock();
                    }
                } else {
                    System.out.println("operation1: lock1 not acquired");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (true);
    }

    public final void operation2() {
        do {
            try {
                if (lock2.tryLock(50, TimeUnit.MILLISECONDS)) {
                    System.out.println("operation2: lock2 acquired");
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (lock1.tryLock(50, TimeUnit.MILLISECONDS)) {
                        System.out.println("operation2: lock1 acquired");
                        lock1.unlock();
                        lock2.unlock();
                        break;
                    } else {
                        System.out.println("operation2: lock1 not acquired");
                        lock2.unlock();
                    }
                } else {
                    System.out.println("operation2: lock2 not acquired");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (true);
    }
}

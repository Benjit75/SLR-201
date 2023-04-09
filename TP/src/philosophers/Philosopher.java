package philosophers;

import static philosophers.Philosopher.State.*;

public class Philosopher extends Thread {

    public enum State {THINKING, HUNGRY, EATING}; // 0 = thinking, 1 = hungry, 2 = eating
    private final int id;
    private final Fork leftFork, rightFork;
    private State state;

    public Philosopher(int id, Fork leftFork, Fork rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.state = THINKING;
    }

    public String toString() {
        String stateString;
        switch (state) {
            case THINKING -> stateString = "thinking";
            case HUNGRY -> stateString = "hungry";
            case EATING -> stateString = "eating";
            default -> stateString = "thinking";
        }
        return "Philosopher [id = " + id + ", leftFork = " + leftFork + ", rightFork = " + rightFork + ", state = " +
                stateString + "]";
    }

    public synchronized void think() {
        this.state = THINKING; // thinking
        Log.msg("Philosopher " + id + " is thinking");
        try {
            Thread.sleep((long) (Math.random() * 256));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        eat();
    }

    public synchronized void eat() {
        this.state = HUNGRY; // hungry
        Log.msg("Philosopher " + id + " is hungry");
        try {
            while (true) {
                if (leftFork.isFree() || leftFork.isTakenBy() == this.id) {
                    leftFork.take(id);
                    if (rightFork.isFree()) {
                        rightFork.take(id);
                        this.state = EATING; // eating
                        Log.msg("Philosopher " + id + " is eating");
                        Thread.sleep(256);
                        leftFork.release();
                        rightFork.release();
                        Log.msg("Philosopher " + id + " has finished eating and released the forks: " + leftFork +
                                " and " + rightFork);
                        notifyAll();
                        break;
                    } else {
                        Log.msg("Philosopher " + id + " is waiting for " + rightFork);
                        wait();
                    }
                } else {
                    Log.msg("Philosopher " + id + " is waiting for " + leftFork);
                    wait();
                }
            }
            think();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        think();
    }

}

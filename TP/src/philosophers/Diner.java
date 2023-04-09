package philosophers;

import java.util.Arrays;

public class Diner {

    public static void main(String[] args) {

        final Fork[] forks = new Fork[5];
        for (int i = 0; i < 5; i++) {
            forks[i] = new Fork(i);
        }

        final Philosopher[] philosophers = new Philosopher[5];
        for (int i = 0; i < 5; i++) {
            philosophers[i] = new Philosopher(i, forks[i], forks[(i + 4) % 5]);
        }

        System.out.println(Arrays.toString(philosophers));

        for (Philosopher philosopher : philosophers) {
            philosopher.start();
        }
    }

}

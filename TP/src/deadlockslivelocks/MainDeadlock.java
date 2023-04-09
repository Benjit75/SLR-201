package deadlockslivelocks;

public class MainDeadlock {

    public static void main(String[] args) {
        MyThread thread1 = new MyThread(1);
        MyThread thread2 = new MyThread(2);
        thread1.start();
        thread2.start();
    }
}
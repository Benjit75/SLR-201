package deadlockslivelocks;

public class MyThread extends Thread{

    private final int type;

    private final DeadlockExample deadlockExample = new DeadlockExample();


    public MyThread(int type){
        this.type = type;
    }

    public void run(){
        switch (this.type) {
            case 1 -> deadlockExample.operation1();
            case 2 -> deadlockExample.operation2();
            default -> deadlockExample.operation1();
        }
    }
}

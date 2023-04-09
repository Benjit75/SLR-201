package livelocks;

public class MyThread extends Thread{

    private final int type;

    private final LivelockExample livelockExample = new LivelockExample();


    public MyThread(int type){
        this.type = type;
    }

    public void run(){
        switch (this.type) {
            case 1 -> livelockExample.operation1();
            case 2 -> livelockExample.operation2();
            default -> livelockExample.operation1();
        }
    }
}

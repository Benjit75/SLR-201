package philosophers;

public class Fork {
    private final int id;
    private int takenBy;

    public Fork(int id) {
        this.id = id;
        this.takenBy = -1;
    }

    public String toString() {
        return "Fork [id = " + id + ", takenBy = " + takenBy + "]";
    }

    public synchronized void take(int id) {
        this.takenBy = id;
    }

    public synchronized void release() {
        this.takenBy = -1;
    }

    public synchronized boolean isFree() {
        return this.takenBy < 0;
    }

    public synchronized int isTakenBy() {
        return this.takenBy;
    }
}

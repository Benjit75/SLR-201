package basics;

public class CommandsBuffer {

    public String[] getCommands() {
        return commands;
    }

    // An array to store the commands to be executed
    private final String[] commands = new String[1024];

    public int getNextStoreIdx() {
        return nextStoreIdx;
    }

    // Index where to store the next arriving command
    // Condition: (nextStoreIdx + 1) % 1024 != lastTakeIdx
    private int nextStoreIdx = 0;

    public int getNextTakeIdx() {
        return nextTakeIdx;
    }

    // Index where to take the next command to execute // Condition: nextTakeIdx != nextStoreIdx
    private int nextTakeIdx = 0;

    public synchronized String pullCommand() {
        try {
            while (true) {
                if (nextTakeIdx == nextStoreIdx) {
                    wait();
                } else {
                    String cmd = commands[nextTakeIdx];
                    nextTakeIdx = (nextTakeIdx + 1) % 1024;
                    notifyAll();
                    return cmd;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public synchronized void pushCommand(String cmd) {
        try {
            while (true) {
                int futureStoreIdx = (nextStoreIdx + 1) % 1024;
                if (nextTakeIdx == futureStoreIdx) {
                    wait();
                } else {
                    commands[nextStoreIdx] = cmd;
                    nextStoreIdx = futureStoreIdx;
                    notifyAll();
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

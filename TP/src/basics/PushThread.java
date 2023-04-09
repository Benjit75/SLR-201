package basics;

public class PushThread extends Thread{
	
	static String name = "pushthread";
	
	private CommandsBuffer cmdBuffer;
	
	public PushThread(CommandsBuffer cmdBuffer) {
		this.cmdBuffer = cmdBuffer;
	}
	
	public void run() {
        for (int i = 0; i < 100; i++) {
            this.cmdBuffer.pushCommand("command " + i);
            System.out.println("Pushed command " + i);
            try {
                Thread.sleep(50);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

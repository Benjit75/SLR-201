package basics;

public class PullThread extends Thread{
	
	static String name = "pullthread";
	
	private CommandsBuffer cmdBuffer;
	
	public PullThread(CommandsBuffer cmdBuffer) {
		this.cmdBuffer = cmdBuffer;
	}
	
	public void run() {
		for (int i=0; i<100; i++) {
			System.out.println(this.cmdBuffer.pullCommand());
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

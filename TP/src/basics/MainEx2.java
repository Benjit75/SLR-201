package basics;

public class MainEx2 {

	public static void main(String[] args) {
		CommandsBuffer cmdBuffer = new CommandsBuffer();
		PullThread pullThread = new PullThread(cmdBuffer);
		PushThread pushThread = new PushThread(cmdBuffer);
		pullThread.start();
		pushThread.start();
		try {
			pushThread.join();
			pullThread.join();
		} catch(Exception e) {};
	}
}

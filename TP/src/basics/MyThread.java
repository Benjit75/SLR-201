package basics;

public class MyThread extends Thread{
	
	private String threadName ;
	
	public MyThread(String threadName) {
		this.threadName = threadName;
	}
	
	public void run() {
		for(int i=0; i<100; i++) {
			System.out.println(this.threadName+"_"+String.valueOf(i));
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(this.threadName+"_END");
	}

}


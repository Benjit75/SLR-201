package basics;

import java.util.ArrayList;

public class MainEx1 {
	static int N = 10;
	
	public static void main(String[] args) {
		ArrayList<MyThread> threads = new ArrayList<MyThread>();
		for (int i=0;i<N;i++) {
			threads.add(new MyThread("thread"+String.valueOf(i)));
			threads.get(i).start();
		}
	}
}

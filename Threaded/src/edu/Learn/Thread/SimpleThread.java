package edu.Learn.Thread;

public class SimpleThread extends Thread{
	
	public SimpleThread(int threadid, int threadDelay) {
		this.id = threadid;
		this.delay = threadDelay;
	}
	
	public void run() {
		System.out.println(id + " Started");
		System.out.flush();
		for (int i = 0; i < 10; i++) {
			try {
				sleep(delay);
			}
			catch(InterruptedException e) {
				System.out.println("sleep interrupted: "+e);
			}
			System.out.println("Thread" + id + ": i = " +i);
		}
		System.out.println("Thread" + id + " finished.");
	}
	
	public static void main(String[] args) {
		SimpleThread t = new SimpleThread(1, 1000);
		t.run();
	}
	
	private int id;
	private int delay;
}

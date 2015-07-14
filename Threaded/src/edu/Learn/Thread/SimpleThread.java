package edu.Learn.Thread;

public class SimpleThread extends Thread{
	
	public SimpleThread(int threadid, int threadDelay) {
		this.id = threadid;
		this.delay = threadDelay;
	}
	
	public void run() {
		
	}
	
	private int id;
	private int delay;
}

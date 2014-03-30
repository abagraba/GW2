package IO;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;



public class TaskQueue<T extends Task> {

	private int							i			= 0;
	private ConcurrentLinkedQueue<T>	queue		= new ConcurrentLinkedQueue<T>();
	private boolean						halt;

	private Thread						queueThread	= new Thread(new Runnable() {
														@Override
														public void run() {
															polling();
														}
													});

	public void produce(T t) {
		queue.add(t);
		synchronized (this) {
			notifyAll();
		}
	}

	public void addTask(T t) {
		queue.add(t);
	}


	public void start() {
		queueThread.setName("Consumer Queue Polling Thread");
		queueThread.start();
	}

	public void halt() {
		this.halt = true;
	}

	private void polling() {
		while (!halt) {
			while (runTask())
				;
			synchronized (this) {
				try {
					wait();
				}
				catch (InterruptedException e) {}
			}
		}
	}
	
	private boolean runTask() {
		T t = queue.poll();
		if (t == null)
			return false;
		t.runTask();
		return true;
	}


}

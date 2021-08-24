package models;

public abstract class MyThreadClass implements Runnable {

	private Thread thread;
	private boolean isExecuted;
	private boolean isPaused;
	private long sleepTime;

	public MyThreadClass() {
		thread = new Thread(this);
		sleepTime = 1;
	}
	
	public abstract void executeTask();

	public void setSleepTime(long time) {
		this.sleepTime = time;
	}

	public synchronized void stop() {
		isExecuted = false;
	}

	public synchronized void pause() {
		isPaused = true;
		notifyAll();
	}

	public synchronized void resume() {
		isPaused = false;
	}

	public void start() {
		isExecuted = true;
		thread.start();
	}

	@Override
	public void run() {
		while (isExecuted) {
			synchronized (this) {
				executeTask();
				if (isPaused) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (!isExecuted) {
						break;
					}
				}
			}
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

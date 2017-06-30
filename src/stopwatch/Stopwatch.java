package stopwatch;

public class Stopwatch implements Runnable {
	
	private volatile boolean running = false;
	private int seconds = 0, minutes = 0, hours = 0;
	
	private StopwatchTaskHandler stopwatchTaskHandler;
	
	private StringBuilder stringBuilder;
	
	public Stopwatch(StopwatchTaskHandler stopwatchTaskHandler) {
		this.stopwatchTaskHandler = stopwatchTaskHandler;
		
		stringBuilder = new StringBuilder();
	}
	
	private void appendTime(int time) {
		if (time < 10) {
			stringBuilder.append("0");
		}
		
		stringBuilder.append(time);
	}
	
	public void start() {
		if (!running) {
			running = true;
			
			new Thread(this).start();
		}
	}
	
	public void pause() {
		if (running) {
			running = false;
		}
	}
	
	public void stop() {
		running = false;
		seconds = minutes = hours = 0;
		
		stopwatchTaskHandler.stopwatchTaskHandled();
	}
	
	@Override
	public void run() {
		while (running) {
			seconds++;
			
			if (seconds == 60) {
				minutes++;
				
				if (minutes == 60) {
					hours++;
					
					if (hours == 24) {
						hours = 0;
					}
					
					minutes = 0;
				}
				
				seconds = 0;
			}
			
			try {
				Thread.sleep(1000);
			}
			catch (Exception exception) {
				exception.printStackTrace();
			}
			
			stopwatchTaskHandler.stopwatchTaskHandled();
		}
	}
	
	@Override
	public String toString() {
		stringBuilder.setLength(0);		// resets string-builder...
		
		appendTime(hours);
		stringBuilder.append(":");
		
		appendTime(minutes);
		stringBuilder.append(":");
		
		appendTime(seconds);
		
		return stringBuilder.toString();
	}
	
}
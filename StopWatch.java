package cecs328.project_2;

public class StopWatch {
	private long elapsedTime;
	private long startTime;
	private boolean isRunning;
	
	public StopWatch()
	{
		reset();
	}
	public void Start()
	{
		if(isRunning){return;}
		isRunning = true;
		startTime = System.nanoTime();
	}
	public void Stop()
	{
		if(!isRunning){return;}
		isRunning = false;
		long endTime = System.nanoTime();
		elapsedTime = endTime - startTime;
	}
	public long getElapsedTime()
	{
		if(isRunning)
		{
			long endTime = System.nanoTime();
			return elapsedTime + endTime - startTime;
		}
		else
		{
			return elapsedTime;
		}
	}
	public void reset()
	{
		elapsedTime = 0;
		isRunning = false;
	}
}

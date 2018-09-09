package edu.nd.se2018.homework3;
import java.lang.Math;

public class Horse {
	private Strategy strategy;
	private int maxSpeed;
	private float distanceRun;
	private String name;
	
	public Horse(RunStrategy runStrategy, int maximumSpeed, String name) {
		switch (runStrategy) {
			case EARLYSPRINT_STRATEGY: 
				this.strategy = new EarlySprintStrategy();
				break;
			case STEADYRUN_STRATEGY:
				this.strategy = new SteadyRunStrategy();
				break;
			case SLOWSTART_STRATEGY:
				this.strategy = new SlowStartStrategy();
				break;
		}
		this.maxSpeed = maximumSpeed;
		this.distanceRun = 0;
		this.name = name;
	}
	
	public void setStrategy(RunStrategy runStrategy) {
		switch (runStrategy) {
			case EARLYSPRINT_STRATEGY: 
				this.strategy = new EarlySprintStrategy();
				break;
			case STEADYRUN_STRATEGY:
				this.strategy = new SteadyRunStrategy();
				break;
			case SLOWSTART_STRATEGY:
				this.strategy = new SlowStartStrategy();
				break;
		}
	}
	
	// timePeriod is in minute
	void run(int timePeriod) {
		// Get percentage running speed
		float percent = this.strategy.getSpeedPercentage(Math.round(distanceRun));
		this.distanceRun += ((float)timePeriod/60) * this.maxSpeed * percent;
	}
	
	public float getDistanceRun() {
		return this.distanceRun;
	}
	
	public String getName() {
		return this.name;
	}
}

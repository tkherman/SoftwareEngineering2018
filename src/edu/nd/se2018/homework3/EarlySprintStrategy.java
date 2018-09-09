package edu.nd.se2018.homework3;

public class EarlySprintStrategy implements Strategy {

	@Override
	public float getSpeedPercentage(int mile) {
		if (mile < 2)
			return 1;
		
		return (float) 0.75;
	}

}

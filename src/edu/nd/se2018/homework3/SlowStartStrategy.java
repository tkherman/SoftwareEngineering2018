package edu.nd.se2018.homework3;

public class SlowStartStrategy implements Strategy{

	@Override
	public float getSpeedPercentage(int mile) {
		if (mile < 6)
			return (float)0.75;
		else if (mile < 9)
			return (float)0.9;
		else
			return 1;
	}

}

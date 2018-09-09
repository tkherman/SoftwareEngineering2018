package edu.nd.se2018.homework3;
import org.junit.Test;

public class HorseRaceTest {
	@Test
	public void strategyTest() {
		Strategy testStrategy = new EarlySprintStrategy();
		assert(testStrategy.getSpeedPercentage(1) == (float)1);
		assert(testStrategy.getSpeedPercentage(2) == (float)0.75);
		assert(testStrategy.getSpeedPercentage(8) == (float)0.75);
		
		testStrategy = new SteadyRunStrategy();
		assert(testStrategy.getSpeedPercentage(2) == (float)0.8);
		assert(testStrategy.getSpeedPercentage(9) == (float)0.8);
		
		testStrategy = new SlowStartStrategy();
		assert(testStrategy.getSpeedPercentage(3) == (float)0.75);
		assert(testStrategy.getSpeedPercentage(5) == (float)0.75);
		assert(testStrategy.getSpeedPercentage(6) == (float)0.9);
		assert(testStrategy.getSpeedPercentage(9) == (float)1);
	}
	
	@Test
	public void horseRunTest() {
		Horse horse = new Horse(RunStrategy.STEADYRUN_STRATEGY, 10, "testHorse");
		horse.run(60);
		assert(horse.getDistanceRun() == (float)8);
		
		// Reset distanceRun
		horse = new Horse(RunStrategy.STEADYRUN_STRATEGY, 10, "testHorse");
		horse.run(1);
		assert(horse.getDistanceRun() == 10 * (float)0.8 * ((float)1/60));
	}
	

	@Test
	public void raceTest() {
		Race race = new Race();
		race.addHorse(new Horse(RunStrategy.STEADYRUN_STRATEGY, 10, "winner"));
		race.addHorse(new Horse(RunStrategy.STEADYRUN_STRATEGY, 8, "loser"));
		String winner = race.startRace();
		System.out.println(winner);
		assert(winner == "winning");
	}
}

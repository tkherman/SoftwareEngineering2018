package edu.nd.se2018.homework3;

public class Main {

	public static void main(String[] args) {
		System.out.println("hello");
		Race race = new Race();
		race.addHorse(new Horse(RunStrategy.STEADYRUN_STRATEGY, 30, "Jason"));
		race.addHorse(new Horse(RunStrategy.SLOWSTART_STRATEGY, 30, "Mary"));
		race.addHorse(new Horse(RunStrategy.EARLYSPRINT_STRATEGY, 30, "Tammy"));
		String winner = race.startRace();
		System.out.println(String.format("%s is the winner", winner));
	}

}

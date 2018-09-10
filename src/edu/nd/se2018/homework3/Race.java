package edu.nd.se2018.homework3;

import java.util.LinkedList;

public class Race {
	float raceLength;
	LinkedList<Horse> horses;
	
	public Race() {
		horses = new LinkedList<Horse>();
		raceLength = 10;
	}
	
	void addHorse(Horse horse) {
		horses.add(horse);
	}
	
	// Assume each loop is a minute
	String startRace() {
		if (horses.size() == 0) {
			return "No horses are added to the race";
		}
		

		while (true) {
			for (Horse h : horses) {
				h.run(1);
				System.out.println(String.format("%s has run %f", h.getName(), h.getDistanceRun()));
				if (h.getDistanceRun() > raceLength) {
					return h.getName();
				}
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

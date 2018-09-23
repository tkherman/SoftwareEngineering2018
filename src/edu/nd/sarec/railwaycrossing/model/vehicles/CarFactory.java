package edu.nd.sarec.railwaycrossing.model.vehicles;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;

import edu.nd.sarec.railwaycrossing.model.infrastructure.Direction;
import edu.nd.sarec.railwaycrossing.model.infrastructure.Road;
import edu.nd.sarec.railwaycrossing.model.infrastructure.gate.CrossingGate;


/**
 * Very basic car factory.  Creates the car and registers it with the crossing gate and the car infront of it.
 * @author jane
 *
 */
public class CarFactory {
	
	private Collection<CrossingGate> gates = null;
	private Car previousCar = null;
	private ArrayList<Car> cars = new ArrayList<Car>();
	private String roadName;
	Direction direction;
	Point location;
	Road junctionRoad;
	
	public CarFactory(){}
	
	public CarFactory(Direction direction, Point location, Collection<CrossingGate> gates, Road junctionRoad, String roadName){
		this.direction = direction;
		this.location = location;
		this.gates = gates;
		this.junctionRoad = junctionRoad;
		this.roadName = roadName;
	}
	
	
	// Most code here is to create random speeds
	public Car buildCar(){
		if (previousCar == null || location.y < previousCar.getVehicleY()-100){
			Car car = new Car(location.x,location.y, Direction.SOUTH);	
			double speedVariable = (Math.random() * 10)/10;
			car.setSpeed((2-speedVariable)*1.5); 
			
			// All cars created by this factory must be aware of crossing gates in the road
			for(CrossingGate gate: gates){
				gate.addObserver(car);
				if(gate != null && gate.getTrafficCommand()=="STOP")
					car.setGateDownFlag(true);
			}
			
			// Each car must observe the car in front of it so it doesn't collide with it.
			if (previousCar != null)
				previousCar.addObserver(car);
			previousCar = car;
			
			cars.add(car);
			return car;
		} else 
			return null;
	}

	// We will get a concurrency error if we try to delete cars whilst iterating through the array list
	// so we perform this in two stages.
	// 1.  Loop through the list and identify which cars are off the screen.  Add them to 'toDelete' array.
	// 2.  Iterate through toDelete and remove the cars from the original arrayList.
	public ArrayList<Car> removeOffScreenCars() {
		// Removing cars from the array list.
		ArrayList<Car> toDelete = new ArrayList<Car>();
		for(Car car: cars){
			if (car.offScreen())
				toDelete.add(car);
			
		}   
		
		for (Car car: toDelete)
			cars.remove(car);
		return toDelete;
	}
	
	public void moveCars() {
		int index;
		ArrayList<Car> crossingCars = new ArrayList<Car>();
		for (Car car : cars) {
			// Handles logic of traffic
			if (roadName == "Skyway") {
				// Make car cross
				if (car.getVehicleY() > junctionRoad.getStartY() - junctionRoad.getRoadWidth() &&
						car.getVehicleY() < junctionRoad.getStartY() + junctionRoad.getRoadWidth()/3 &&
						(int)(Math.random() * 100) < 5) {
					car.setDirection(Direction.WEST);
					index = cars.indexOf(car);
					
					// Car is sandwiched between a leading car and a trailing car
					if (index > 0 && index < cars.size() - 1) {
						Car leadCar = cars.get(index-1);
						Car trailCar = cars.get(index+1);
						
						// fix traffic
						car.deleteObserver(trailCar);
						leadCar.addObserver(trailCar);
						leadCar.deleteObserver(car);
					}
					
					// Car is the leading car
					if (index == 0 && index < cars.size() - 1) {
						Car trailCar = cars.get(index+1);
						car.deleteObserver(trailCar);
						trailCar.removeLeadCar();
					}
					
					junctionRoad.getCarFactory().cars.add(car);
					crossingCars.add(car);
				}
			} else if (roadName == "EastWest") {
				if (car.getVehicleX() == 391) {
					car.setDirection(Direction.SOUTH);
					crossingCars.add(car);
					
					// Find the position that the car should be inserted into Western Road
					ArrayList<Car> junctionRoadCars = junctionRoad.getCarFactory().getCars();
					Car newLeadCar = null;
					Car newTrailCar = null;
					for (Car junctionCar : junctionRoadCars) {
						if (junctionCar.getVehicleY() < car.getVehicleY()) {
							newTrailCar = junctionCar;
							break;
						}
						newLeadCar = junctionCar;
					}
					
					int trailIndex;
					// Empty road
					if (newLeadCar == null && newTrailCar == null) {
						junctionRoadCars.add(car);
					// There's car in front and behind
					} else if (newTrailCar != null && newLeadCar != null) {
						newTrailCar.removeLeadCar();
						car.removeLeadCar();
						newLeadCar.deleteObserver(newTrailCar);
						newLeadCar.addObserver(car);
						car.addObserver(newTrailCar);
						trailIndex = junctionRoadCars.indexOf(newTrailCar);
						junctionRoadCars.add(trailIndex, car);
					// There's no car in front
					} else if (newTrailCar != null && newLeadCar == null) {
						newTrailCar.removeLeadCar();
						car.removeLeadCar();
						car.addObserver(newTrailCar);
						junctionRoadCars.add(0, car);
					// There's no car behind
					} else if (newLeadCar != null && newTrailCar == null){
						newLeadCar.addObserver(car);
						junctionRoadCars.add(car);
					}
				}
			}

			car.move();
		}
		
		for (Car c : crossingCars) {
			cars.remove(c);
		}
		
		return;
	}
	
	public ArrayList<Car> getCars() {
		return cars;
	}
}

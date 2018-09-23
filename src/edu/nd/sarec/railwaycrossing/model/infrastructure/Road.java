package edu.nd.sarec.railwaycrossing.model.infrastructure;

import java.awt.Point;
import java.util.Collection;
import java.util.Vector;

import edu.nd.sarec.railwaycrossing.model.infrastructure.gate.CrossingGate;
import edu.nd.sarec.railwaycrossing.model.vehicles.CarFactory;

/**
 * Represents a single road
 * @author jane
 *
 */
public class Road {
	private int startX;
	private int endX;
	private int startY;
	private int endY;
	private String name;
	private CarFactory carFactory;
	private Road junctionRoad;
	Direction direction;
	Collection<CrossingGate> gates;
	boolean clearEnds = false;
	int roadSize;
	
	public Road(){}
	
	public Road(String name, Point start, Point end, Direction direction, boolean buildCarFactory, boolean clearEnds, Road junctionRoad){
		startX = start.x;
		startY = start.y;
		endX = end.x;
		endY = end.y;
		roadSize = 18;
		
		this.name = name;
		this.junctionRoad = junctionRoad;
		this.direction = direction;
		gates = new Vector<CrossingGate>();
		this.clearEnds = clearEnds;
		
	}
	
	// Adds a gate to a road
	// In case a new gate is added after the factory is assigned, we reassign factory
	// The factory needs to know all gates on the road in order to register each car as an observer.
	public void assignGate(CrossingGate gate){
		gates.add(gate);
		if (carFactory != null)
			carFactory = new CarFactory(direction, new Point(startX-roadSize/2,startY), gates, junctionRoad, name);  // allows additional gates.  Needs fixing
	}
	
	public void addCarFactory(){
		if (carFactory == null) // We only allow one
			carFactory = new CarFactory(direction, new Point(startX-roadSize/2,startY), gates, junctionRoad, name);
	}
	
	public CarFactory getCarFactory(){
		return carFactory;
	}
	
	public int getStartX(){
		return startX;
	}
	
	public int getEndX(){
		return endX;
	}
	
	public int getStartY(){
		return startY;
	}
	
	public int getEndY(){
		return endY;
	} 
	
	public Direction getDirection(){
		return direction;
	}
	
	public boolean getClearEnds(){
		return clearEnds;
	}
	
	public int getRoadWidth(){
		return roadSize;
	}
	
	public String getName() {
		return name;
	}
}
package edu.nd.sarec.railwaycrossing.model.infrastructure;

import java.awt.Point;
import java.util.Collection;
import java.util.HashMap;

import edu.nd.sarec.railwaycrossing.model.infrastructure.gate.CrossingGate;

/**
 * Creates all infrastructure for the simulation
 * @author jane
 *
 */
public class MapBuilder {
	HashMap<String, Road> roads;
	HashMap<String, CrossingGate> gates;
	HashMap<String, RailwayTracks> tracks;
	
	public MapBuilder(){
		roads = new HashMap<String,Road>();	
		gates = new HashMap<String,CrossingGate>();
		tracks = new HashMap<String,RailwayTracks>();
		buildRoads();
		buildCrossingGates();
		buildTracks();
		assignGatesToRoads();
		buildCarFactories();
	}

	private void buildRoads(){
		Road westRoad = new Road("Western Highway",new Point(400,0),new Point (400,1000),Direction.SOUTH,true,false,null);
		Road crossing = new Road("EastWest",new Point(415,800),new Point (785,800),Direction.EAST,true,true,westRoad);
		Road eastRoad = new Road("Skyway",new Point(800,0),new Point (800,1000),Direction.SOUTH,true,false,crossing);
		roads.put("EastWest", crossing);	
		roads.put("Skyway", eastRoad);
		roads.put("Western Highway", westRoad);		
	}
	
	private void buildCrossingGates(){
		gates.put("Gate1", new CrossingGate(780,480, "Gate1"));
		gates.put("Gate2", new CrossingGate(380,480, "Gate2"));		
	}
	
	private void buildTracks(){
		tracks.put("Royal", new RailwayTracks(new Point(0,500),new Point(1200,500)));
		tracks.put("Fast", new RailwayTracks(new Point(0, 550), new Point(1200, 550)));
	}
	
	private void assignGatesToRoads(){
		roads.get("Western Highway").assignGate(gates.get("Gate1"));
		roads.get("Skyway").assignGate(gates.get("Gate2"));
	}
	
	private void buildCarFactories(){
		roads.get("Western Highway").addCarFactory();
		roads.get("Skyway").addCarFactory();
		roads.get("EastWest").addCarFactory();
	}
	
	public Collection<CrossingGate> getAllGates(){
		return gates.values();
	}
	
	public Collection<RailwayTracks> getTracks(){
		return tracks.values();
	}
	
	public Collection<Road> getRoads(){
		return roads.values();
	}
	
	public RailwayTracks getTrack(String name){
		return tracks.get(name);
	}
}

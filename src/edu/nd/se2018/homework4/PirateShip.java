package edu.nd.se2018.homework4;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import java.lang.Math;

public class PirateShip implements Observer {
	private Point currentLocation;
	private OceanMap oceanMap;
	
	public PirateShip(int startx, int starty, OceanMap oceanMap, ColumbusShip columbusShip) {
		columbusShip.addObserver(this);
		currentLocation = new Point(startx, starty);
		this.oceanMap = oceanMap;
	}
	
	public Point getShipLocation() {
		return currentLocation;
	}
	
	@Override
	public void update(Observable columbusShip, Object arg1) {
		Point columbusPos = ((ColumbusShip)columbusShip).getShipLocation();
		int xDiff = columbusPos.x - currentLocation.x;
		int yDiff = columbusPos.y - currentLocation.y;
		
		// Pirate is at Columbus' location
		if (xDiff == 0 && yDiff == 0) {
			return;
		// Pirate is parallel to Columbus
		} else if (xDiff == 0 && yDiff != 0) {
			moveY(yDiff/Math.abs(yDiff));
		} else if (xDiff != 0 && yDiff == 0) {
			moveX(xDiff/Math.abs(xDiff));
		// Pirate is further from y direction so try move vertically
		// If not successful, try move horizontally
		} else if (Math.abs(xDiff) < Math.abs(yDiff)) {
			if (!moveY(yDiff/Math.abs(yDiff))) 
				moveX(xDiff/Math.abs(xDiff));
		// Pirate is further from x direction so try move horizontally
		// If not successful, try move vertically
		} else if (Math.abs(xDiff) >= Math.abs(yDiff)){
			if (!moveX(xDiff/Math.abs(xDiff)))
				moveY(yDiff/Math.abs(yDiff));
		}
	}
	
	// Follow the direction provided and move horizontally if no island is blocking
	private boolean moveX(int direction) {
		int newX = currentLocation.x + direction;
		if (newX >= 0 && newX < oceanMap.dimensions && 
				oceanMap.oceanGrid[newX][currentLocation.y] !=  GridType.ISLAND) {
			currentLocation.x = newX;
			return true;
		}
		
		return false;
	}
	
	// Follow the direction provided and move vertically if no island is blocking
	private boolean moveY(int direction) {
		int newY = currentLocation.y + direction;
		if (newY >= 0 && newY < oceanMap.dimensions && 
				oceanMap.oceanGrid[currentLocation.x][newY] !=  GridType.ISLAND) {
			currentLocation.y = newY;
			return true;
		}
		
		return false;
	}

}

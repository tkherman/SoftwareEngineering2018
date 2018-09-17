package edu.nd.se2018.homework4;

import java.awt.Point;
import java.util.Observable;

public class ColumbusShip extends Observable{
	public Point currentLocation;
	private OceanMap oceanMap;
	
	public ColumbusShip(int startx, int starty, OceanMap oceanMap) {
		currentLocation = new Point(startx, starty);
		this.oceanMap = oceanMap;
	}
	
	public void goEast() {
		if (currentLocation.x + 1 < oceanMap.dimensions &&
				oceanMap.oceanGrid[currentLocation.x+1][currentLocation.y] != GridType.ISLAND) {
			currentLocation.x += 1;
			setChanged();
			notifyObservers();
		}
	}
	
	public void goWest() {
		if (currentLocation.x > 0 && 
				oceanMap.oceanGrid[currentLocation.x-1][currentLocation.y] != GridType.ISLAND) {
			currentLocation.x -= 1;
			setChanged();
			notifyObservers();
		}
	}
	
	public void goNorth() {
		if (currentLocation.y > 0 &&
				oceanMap.oceanGrid[currentLocation.x][currentLocation.y - 1] != GridType.ISLAND) {
			currentLocation.y -= 1;
			setChanged();
			notifyObservers();
		}
	}
	
	public void goSouth() {
		if (currentLocation.y + 1 < oceanMap.dimensions &&
				oceanMap.oceanGrid[currentLocation.x][currentLocation.y + 1] != GridType.ISLAND) {
			currentLocation.y += 1;
			setChanged();
			notifyObservers();
		}
	}
	
	public Point getShipLocation() {
		return currentLocation;
	}
}

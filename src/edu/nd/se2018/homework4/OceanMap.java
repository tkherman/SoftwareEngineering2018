package edu.nd.se2018.homework4;

import java.awt.Point;
import java.util.concurrent.ThreadLocalRandom;;

public class OceanMap {
	public GridType[][] oceanGrid;
	public int dimensions;
	private int islandCount;
	
	public OceanMap(int dimensions, int islandCount) {
		this.dimensions = dimensions;
		this.oceanGrid = new GridType[this.dimensions][this.dimensions];
		this.islandCount = islandCount;
		init_grid();
	}
	
	public GridType[][] getMap() {
		return oceanGrid;
	}
	
	public Point getFreeOceanCoord() {
		int randx, randy;
		Point coord = null;
		boolean found = false;

		while (!found) {
			randx = ThreadLocalRandom.current().nextInt(0, dimensions);
			randy = ThreadLocalRandom.current().nextInt(0, dimensions);
			
			if (oceanGrid[randx][randy] == GridType.OCEAN) {
				coord = new Point(randx, randy);
				oceanGrid[randx][randy] = GridType.SHIP;
				found = true;
			}
		}
		
		return coord;
	}
	
	private void init_grid() {
		// Set all grids to oceans
		for (int x = 0; x < dimensions; x++) {
			for (int y = 0; y < dimensions; y++) {
				oceanGrid[x][y] = GridType.OCEAN;
			}
		}
		
		// Set 10 random grid to island
		int randx, randy;
		int islandPlaced = 0;
		while (islandPlaced < islandCount) {
			randx = ThreadLocalRandom.current().nextInt(0, dimensions);
			randy = ThreadLocalRandom.current().nextInt(0, dimensions);
			
			if (oceanGrid[randx][randy] == GridType.ISLAND)
				continue;
			
			oceanGrid[randx][randy] = GridType.ISLAND;
			islandPlaced++;
		}
	}
}

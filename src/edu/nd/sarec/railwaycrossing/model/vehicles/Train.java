package edu.nd.sarec.railwaycrossing.model.vehicles;

import java.util.Observable;

import edu.nd.sarec.railwaycrossing.model.infrastructure.Direction;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents the train entity object
 * @author jane
 *
 */
public class Train extends Observable implements IVehicle{
	private double currentX = 0;
	private double currentY = 0;
	private double originalX = 0;
	private Image img;
	private ImageView imgView;
	private int trainLength = 35;
	private Direction direction;
	private String name;
	
	public Train(int x, int y, Direction dir, String name){
		this.currentX = x;
		this.currentY = y;
		this.direction = dir;
		this.name = name;
		originalX = x;
		img = new Image("images\\Train.PNG",120,trainLength,false,false);
		imgView = new ImageView(img);
		imgView.setX(currentX);
		imgView.setY(currentY);
	}
	
	public double getVehicleX(){
		return currentX;
	}
	
	public double getVehicleY(){
		return currentY;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public String getName() {
		return name;
	}
	
	public void move(){
		if (direction == Direction.WEST)
			currentX-=2;
		else if (direction == Direction.EAST)
			currentX+=2;
		imgView.setX(currentX);
		setChanged();
		notifyObservers();
	}
	
	public boolean offScreen(){
		if (currentX < -200 || currentX > 1400)
			return true;
		else
			return false;				
	}
	
	public void reset(){
		currentX = originalX;
	}

	//@Override
	public Node getImageView() {
		return imgView;
	}
}
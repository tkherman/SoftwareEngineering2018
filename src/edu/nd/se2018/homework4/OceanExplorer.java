package edu.nd.se2018.homework4;

import java.awt.Point;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class OceanExplorer extends Application {
	GridType[][] islandMap;
	final int dimensions = 10;
	final int cellSize = 50;
	final int islandCount = 10;
	AnchorPane root;
	Scene scene;
	Image columbusImage;
	ImageView columbusImageView;
	Image pirateImage;
	ImageView pirateImageView1;
	ImageView pirateImageView2;
	OceanMap oceanMap;
	ColumbusShip columbusShip;
	PirateShip pirateShip1;
	PirateShip pirateShip2;
	
	@Override
	public void start(Stage oceanStage) throws Exception {
		oceanMap = new OceanMap(dimensions, islandCount);
		root = new AnchorPane();
		islandMap = oceanMap.getMap();
		drawMap();
		
		Point startCoord = oceanMap.getFreeOceanCoord();
		columbusShip = new ColumbusShip(startCoord.x, startCoord.y, oceanMap);
		loadColumbusShipImage();
		loadAndInitPirates();
		
		scene = new Scene(root, dimensions*cellSize, dimensions*cellSize);
		oceanStage.setScene(scene);
		oceanStage.setTitle("Chirstopher Columbus Sails the Ocean Blue");
		oceanStage.show();
		startSailing();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void drawMap() {
		for (int x = 0; x < dimensions; x++) {
			for (int y = 0; y < dimensions; y++) {
				Rectangle rect = new Rectangle(x*cellSize, y*cellSize, cellSize, cellSize);
				rect.setStroke(Color.BLACK);
				if (islandMap[x][y] == GridType.OCEAN) {
					rect.setFill(Color.PALETURQUOISE);
				} else if (islandMap[x][y] == GridType.ISLAND) {
					rect.setFill(Color.GREEN);
				}
				root.getChildren().add(rect);
			}
		}
	}
	
	private void startSailing() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				switch(ke.getCode()) {
					case RIGHT:
						columbusShip.goEast();
						break;
					case LEFT:
						columbusShip.goWest();
						break;
					case UP:
						columbusShip.goNorth();
						break;
					case DOWN:
						columbusShip.goSouth();
						break;
					default:
						break;
				}
				columbusImageView.setX(columbusShip.getShipLocation().x*cellSize);
				columbusImageView.setY(columbusShip.getShipLocation().y*cellSize);
				pirateImageView1.setX(pirateShip1.getShipLocation().x*cellSize);
				pirateImageView1.setY(pirateShip1.getShipLocation().y*cellSize);
				pirateImageView2.setX(pirateShip2.getShipLocation().x*cellSize);
				pirateImageView2.setY(pirateShip2.getShipLocation().y*cellSize);
				if (columbusShip.getShipLocation().equals(pirateShip1.getShipLocation()) ||
						columbusShip.getShipLocation().equals(pirateShip2.getShipLocation())) {
					System.out.println("The pirates got you!");
					Platform.exit();
				}

			}
		});
	}
	
	private void loadColumbusShipImage() {
		columbusImage = new Image("images\\ColumbusShip.png", cellSize, cellSize, false, true);
		columbusImageView = new ImageView(columbusImage);
		columbusImageView.setX(columbusShip.getShipLocation().x*cellSize);
		columbusImageView.setY(columbusShip.getShipLocation().y*cellSize);
		root.getChildren().add(columbusImageView);
	}
	
	private void loadAndInitPirates() {
		pirateImage = new Image("images\\pirateship.gif", cellSize, cellSize, false, true);
		Point startCoord;
		
		startCoord = oceanMap.getFreeOceanCoord();
		pirateShip1 = new PirateShip(startCoord.x, startCoord.y, oceanMap, columbusShip);
		pirateImageView1 = new ImageView(pirateImage);
		pirateImageView1.setX(pirateShip1.getShipLocation().x*cellSize);
		pirateImageView1.setY(pirateShip1.getShipLocation().y*cellSize);
		root.getChildren().add(pirateImageView1);
		
		startCoord = oceanMap.getFreeOceanCoord();
		pirateShip2 = new PirateShip(startCoord.x, startCoord.y, oceanMap, columbusShip);
		pirateImageView2 = new ImageView(pirateImage);
		pirateImageView2.setX(pirateShip2.getShipLocation().x*cellSize);
		pirateImageView2.setY(pirateShip2.getShipLocation().y*cellSize);
		root.getChildren().add(pirateImageView2);
		
	}
}

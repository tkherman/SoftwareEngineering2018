package edu.nd.se2018.chipschallenge.view;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class GameObjView {
	private int gridSize;
	private ImageView imgView;
	
	abstract void draw(Pane root);
}

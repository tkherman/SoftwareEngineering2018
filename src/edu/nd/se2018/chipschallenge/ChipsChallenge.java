package edu.nd.se2018.chipschallenge;


import edu.nd.se2018.chipschallenge.model.GameMapBuilder;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ChipsChallenge extends Application{

	private Pane root;
	private Scene scene;
	private GameMapBuilder gameMapBuilder;
	
	@Override
	public void start(Stage stage) throws Exception {
		root = new Pane();
		
		// Build the map
		gameMapBuilder = new GameMapBuilder();
		
		scene = new Scene(root, 1000, 1000);
		stage.setTitle("ChipsChallenge");
		stage.setScene(scene);
		stage.show();
		
		new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

					@Override
					public void handle(KeyEvent ke) {
						switch(ke.getCode()) {
							case RIGHT:
								stage.close();
								break;
							case ESCAPE:
								stage.close();
								break;
							default:
								break;
						}
					}
					
				});
			}
		}.start();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}

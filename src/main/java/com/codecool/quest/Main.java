package com.codecool.quest;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Game;
import com.codecool.quest.logic.GameMap;
import com.codecool.quest.logic.MapLoader;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    private static GameMap map = MapLoader.loadMap();
    private static  Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    private static GraphicsContext context = canvas.getGraphicsContext2D();
    private Stage stage;
    private Label speedLabel = new Label("Speed: ");
    private Game game = new Game(map);

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        stage = primaryStage;
        GridPane ui = new GridPane();
        Button pauseButton = new Button("Pause");
        Button resumeButton = new Button("Resume");
        resumeButton.setVisible(false);

        Slider slider = new Slider();
        slider.setMinWidth(1000);
        slider.setPadding(new Insets(10));
        slider.setMin(0.1);
        slider.setMax(3);
        slider.setValue(1);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setMajorTickUnit(0.1);
        speedLabel.setText("To next change: " + slider.getValue() + " second(s)");

        game.init(slider.getValue());
        handleButtons(pauseButton, resumeButton);
        handleSlider(slider);

        ui.add(pauseButton,0,0);
        ui.add(resumeButton,1,0);
        ui.add(slider,2,0);
        ui.add(speedLabel, 3,0);
        ui.setPadding(new Insets(20));

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(canvas);
        borderPane.setBottom(ui);

        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Game of life");
        stage.show();
    }

    private void handleButtons(Button pauseButton, Button resumeButton) {
        pauseButton.setOnAction(event -> {
            game.pause();
            resumeButton.setVisible(true);
        });
        resumeButton.setOnAction(event -> {
            game.continueGame();
            resumeButton.setVisible(false);
        });
    }

    private void handleSlider(Slider slider) {
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            game.stop();
            speedLabel.setText("To next change: " + game.getTimeToChange() + " second(s)");
            game.init((double) newValue);
        });
    }

    public static void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getItem().isAlive()) {
                    Tiles.drawTile(context, cell.getItem(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
    }
}

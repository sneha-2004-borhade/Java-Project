package com.home;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class SplashScreen extends Application {

    @Override
    public void start(Stage stage) {
        Text title = new Text("glamONrent");
        title.setFont(Font.font("Segoe UI", 64));
        title.setFill(Color.WHITE);
        title.setEffect(new DropShadow(20, Color.web("#FF69B4")));

        title.setTranslateX(-450);
        title.setTranslateY(-300);

        Button getStarted = new Button("Get Started");
        getStarted.setFont(Font.font("Segoe UI", 22));
        getStarted.setStyle("-fx-background-color: #FF69B4; -fx-text-fill: white; -fx-background-radius: 30;");
        getStarted.setVisible(false);

        getStarted.setOnMouseEntered(e -> getStarted.setStyle("-fx-background-color: #ff85c1; -fx-text-fill: white; -fx-background-radius: 30;"));
        getStarted.setOnMouseExited(e -> getStarted.setStyle("-fx-background-color: #FF69B4; -fx-text-fill: white; -fx-background-radius: 30;"));;
getStarted.setOnAction(e -> {
    // SignUp signUp = new SignUp();
    // VBox root = signUp.createScene(() -> {}); // back Runnable not needed for splash
    // signUp.primaryStage = stage; 
    // Scene signUpScene = new Scene(root, 1500, 800);
    // signUp.page1Scene = signUpScene; 
    // stage.setScene(signUpScene);
    // stage.setTitle("Create Account");

    Select_role sR = new Select_role();
    VBox root = sR.createScene(() -> {});
    sR.primaryStage = stage; 
    Scene selectRoleScene = new Scene(root, 1500, 800);
    sR.page1Scene = selectRoleScene; 
    stage.setScene(selectRoleScene);
    //stage.setTitle("Create Account");
});
   
        VBox content = new VBox(60);
        content.setAlignment(Pos.CENTER);
        content.setTranslateY(100); 
        content.getChildren().addAll(title, getStarted);

        Pane animatedBg = new Pane();
        animatedBg.setStyle("-fx-background-color: linear-gradient(to bottom right, #2e003e, #8b1c7b, #ff66cc);");

        Random rand = new Random();
        for (int i = 0; i < 25; i++) {
            Circle circle = new Circle(rand.nextInt(20) + 10);
            circle.setFill(Color.web("#ffffff30"));
            circle.setTranslateX(rand.nextInt(1500));
            circle.setTranslateY(rand.nextInt(800));

            TranslateTransition floatUp = new TranslateTransition(Duration.seconds(6 + rand.nextInt(3)), circle);
            floatUp.setByY(-150);
            floatUp.setCycleCount(Animation.INDEFINITE);
            floatUp.setAutoReverse(true);
            floatUp.setDelay(Duration.seconds(rand.nextDouble() * 2));
            floatUp.play();

            animatedBg.getChildren().add(circle);
        }

        StackPane root = new StackPane(animatedBg, content);
        root.setPadding(new Insets(60));

        Scene scene = new Scene(root, 1500, 800);
        stage.setScene(scene);
        stage.setTitle("glamONrent Splash");
        stage.show();

        TranslateTransition moveToCenter = new TranslateTransition(Duration.seconds(2), title);
        moveToCenter.setToX(0);
        moveToCenter.setToY(0);

        ScaleTransition pulse1 = new ScaleTransition(Duration.seconds(0.4), title);
        pulse1.setToX(1.2);
        pulse1.setToY(1.2);
        pulse1.setAutoReverse(true);
        pulse1.setCycleCount(2);

        ScaleTransition pulse2 = new ScaleTransition(Duration.seconds(0.4), title);
        pulse2.setToX(1.2);
        pulse2.setToY(1.2);
        pulse2.setAutoReverse(true);
        pulse2.setCycleCount(2);

        SequentialTransition sequence = new SequentialTransition(
                moveToCenter,
                new PauseTransition(Duration.seconds(0.3)),
                pulse1,
                new PauseTransition(Duration.seconds(0.4)),
                pulse2
        );

        sequence.setOnFinished(e -> getStarted.setVisible(true));
        sequence.play();
    }
}











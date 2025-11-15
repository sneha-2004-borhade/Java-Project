package com.home;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class OrderConfirmedPage {

    private Stage stage;

    public OrderConfirmedPage(Stage stage) {
        this.stage = stage;
    }

    public VBox getView(Runnable onBack) {
        VBox root = new VBox(20);
        root.setPadding(new Insets(50));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #fce4ec, #e6e6fa);");

        Text confirmation = new Text("🎉 Your order has been confirmed!");
        confirmation.setFont(Font.font("Georgia", 50));
        confirmation.setFill(Color.DARKMAGENTA);

        ImageView checkmark = new ImageView(new Image("https://img.icons8.com/ios-filled/100/26e07f/checked--v1.png"));
        checkmark.setFitWidth(80);
        checkmark.setFitHeight(80);
        checkmark.setOpacity(0);

        FadeTransition fadeCheck = new FadeTransition(Duration.seconds(0.5), checkmark);
        fadeCheck.setFromValue(0);
        fadeCheck.setToValue(1);

        ScaleTransition scaleUp = new ScaleTransition(Duration.seconds(0.5), checkmark);
        scaleUp.setFromX(0);
        scaleUp.setFromY(0);
        scaleUp.setToX(1.5);
        scaleUp.setToY(1.5);

        ScaleTransition scaleDown = new ScaleTransition(Duration.seconds(0.5), checkmark);
        scaleDown.setFromX(1.5);
        scaleDown.setFromY(1.5);
        scaleDown.setToX(1);
        scaleDown.setToY(1);

        SequentialTransition scaleBounce = new SequentialTransition(scaleUp, scaleDown);

        SequentialTransition fullCheckAnim = new SequentialTransition(fadeCheck, scaleBounce);
        fullCheckAnim.play();

        Text sparkle = new Text("✨ Thank you for renting with GlamOnRent ✨");
        sparkle.setFont(Font.font("Georgia", 40));
        sparkle.setFill(Color.DARKVIOLET);
        sparkle.setOpacity(0);

        FadeTransition sparkleFade = new FadeTransition(Duration.seconds(2), sparkle);
        sparkleFade.setFromValue(0);
        sparkleFade.setToValue(1);
        sparkleFade.setDelay(Duration.seconds(1));
        sparkleFade.play();

        Button backButton = new Button("← Back");
        backButton.setStyle("-fx-background-color: #DA70D6; -fx-text-fill: white; -fx-font-size: 16px;");
        backButton.setOnMouseEntered(e -> backButton.setStyle("-fx-background-color:#8743abff; -fx-text-fill: white; -fx-cursor: hand;"));
        backButton.setOnMouseExited(e -> backButton.setStyle("-fx-background-color: #DA70D6; -fx-text-fill: white; -fx-cursor: hand;"));
       /// backButton.setOnAction(e -> onBack.run());


        backButton.setOnAction(e -> {
          OrderManager.clearOrders();  
          onBack.run();                
        });

        root.getChildren().addAll(checkmark, confirmation, sparkle, backButton);
        return root;
    }
}

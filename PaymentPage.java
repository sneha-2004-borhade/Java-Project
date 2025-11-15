package com.home;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class PaymentPage {

    public VBox getView(Stage stage, Image image, String title, int priceValue, Runnable onBack) {
        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #fff0f5;");

        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(300);
        imageView.setFitWidth(300);

        Text titleText = new Text(title);
        titleText.setFont(Font.font("Georgia", 20));
        titleText.setFill(Color.DARKMAGENTA);

        Text price = new Text("Original Price: ₹" + priceValue);
        price.setFont(Font.font("Arial", 20));

        int discountAmount = CouponUtil.getDiscountAmount("₹" + priceValue);
        Text discount = new Text("🎉 15% Coupon Applied! You save ₹" + discountAmount);
        discount.setFont(Font.font("Arial", 40));
        discount.setFill(Color.FORESTGREEN);

        StackPane sparklePane = new StackPane();
        sparklePane.setPrefHeight(300);
        sparklePane.setMaxWidth(1200);

        Pane top = new Pane();
        top.setPrefSize(1200, 75);
        StackPane.setAlignment(top, Pos.TOP_CENTER);

        Pane bottom = new Pane();
        bottom.setPrefSize(1200, 75);
        StackPane.setAlignment(bottom, Pos.BOTTOM_CENTER);

        Pane left = new Pane();
        left.setPrefSize(75, 300);
        StackPane.setAlignment(left, Pos.CENTER_LEFT);

        Pane right = new Pane();
        right.setPrefSize(75, 300);
        StackPane.setAlignment(right, Pos.CENTER_RIGHT);

        sparklePane.getChildren().addAll(top, bottom, left, right);

        celebrateSparkles(top, "DOWN");
        celebrateSparkles(bottom, "UP");
        celebrateSparkles(left, "RIGHT");
        celebrateSparkles(right, "LEFT");

        Button continueBtn = new Button("Confirm & Continue");
        continueBtn.setStyle("-fx-background-color: #DA70D6; -fx-text-fill: white; -fx-font-size: 20px;");
        continueBtn.setOnMouseEntered(e -> continueBtn.setStyle("-fx-background-color:#8743abff; -fx-text-fill: white;"));
        continueBtn.setOnMouseExited(e -> continueBtn.setStyle("-fx-background-color: #DA70D6; -fx-text-fill: white;"));

        continueBtn.setOnAction(e -> {
            AddressShippingPage addressPage = new AddressShippingPage();
            Scene scene = new Scene(addressPage.getView(stage, image, title, priceValue, onBack), 1500, 800);
            stage.setScene(scene);
        });

        root.getChildren().addAll(imageView, titleText, price, discount, sparklePane, continueBtn);
        return root;
    }

    private void celebrateSparkles(Pane pane, String direction) {
        Random random = new Random();
        Color[] colors = {
            Color.HOTPINK, Color.LIGHTBLUE, Color.LIMEGREEN,
            Color.ORANGE, Color.YELLOW, Color.CORNFLOWERBLUE,
            Color.MEDIUMVIOLETRED, Color.GOLD, Color.DEEPSKYBLUE,
            Color.SALMON
        };

        for (int i = 0; i < 25; i++) {
            double radius = 2 + random.nextDouble() * 3;
            Color color = colors[random.nextInt(colors.length)];

            Circle sparkle = new Circle(radius, color);
            sparkle.setOpacity(0.9);

            double startX = random.nextDouble() * pane.getPrefWidth();
            double startY = random.nextDouble() * pane.getPrefHeight();
            sparkle.setLayoutX(startX);
            sparkle.setLayoutY(startY);

            TranslateTransition move = new TranslateTransition(Duration.seconds(1.5), sparkle);
            switch (direction) {
                case "UP" -> move.setByY(-50 - random.nextDouble() * 50);
                case "DOWN" -> move.setByY(50 + random.nextDouble() * 50);
                case "LEFT" -> move.setByX(-50 - random.nextDouble() * 50);
                case "RIGHT" -> move.setByX(50 + random.nextDouble() * 50);
            }

            FadeTransition fade = new FadeTransition(Duration.seconds(1.5), sparkle);
            fade.setFromValue(1.0);
            fade.setToValue(0.0);

            ParallelTransition sparkleAnim = new ParallelTransition(sparkle, move, fade);
            sparkleAnim.setCycleCount(1);
            sparkleAnim.play();

            pane.getChildren().add(sparkle);
        }
    }
}

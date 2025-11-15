package com.home;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ProductDescriptionPage {

    public VBox getView(Stage stage, Image image, String title, Runnable onBack) {
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #fff0f5;");
        root.setAlignment(Pos.CENTER);

        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(400);
        imageView.setFitWidth(400);

        Text titleText = new Text(title);
        titleText.setFont(Font.font("Arial", 24));
        titleText.setFill(Color.DARKMAGENTA);

        Text description = new Text("This is a premium rental item with top quality. Great for your next occasion.");
        description.setFont(Font.font("Arial", 16));
        description.setWrappingWidth(300);

        ComboBox<Integer> daysComboBox = new ComboBox<>();
        daysComboBox.setPromptText("Select Days");
        for (int i = 1; i <= 7; i++) {
            daysComboBox.getItems().add(i);
        }

        Text daysSelectedText = new Text();
        daysSelectedText.setFont(Font.font("Arial", 16));
        daysSelectedText.setFill(Color.DARKSLATEBLUE);

        daysComboBox.setOnAction(e -> {
            Integer selected = daysComboBox.getValue();
            if (selected != null) {
                daysSelectedText.setText("Selected: " + selected + " day(s)");
            } else {
                daysSelectedText.setText("");
            }
        });

        Button confirmOrder = new Button("Confirm Order");
        confirmOrder.setStyle("-fx-background-color: #DA70D6; -fx-text-fill: white; -fx-font-size: 16px;");
        confirmOrder.setOnMouseEntered(e -> confirmOrder.setStyle("-fx-background-color:#8743abff; -fx-text-fill: white; -fx-cursor: hand;"));
        confirmOrder.setOnMouseExited(e -> confirmOrder.setStyle("-fx-background-color: #DA70D6; -fx-text-fill: white; -fx-cursor: hand;"));

        confirmOrder.setOnAction(e -> {
            Integer days = daysComboBox.getValue();
            if (days != null) {
                OrderManager.addOrder(image, title, days);
                int price = days * 299;
                PaymentPage paymentPage = new PaymentPage();
                Scene scene = new Scene(paymentPage.getView(stage, image, title, price, onBack), 1500, 800);
                stage.setScene(scene);
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> onBack.run());
        backButton.setStyle("-fx-background-color: #DA70D6; -fx-text-fill: white; -fx-font-size: 16px;");
        backButton.setOnMouseEntered(e -> backButton.setStyle("-fx-background-color:#8743abff; -fx-text-fill: white; -fx-cursor: hand;"));
        backButton.setOnMouseExited(e -> backButton.setStyle("-fx-background-color: #DA70D6; -fx-text-fill: white; -fx-cursor: hand;"));

        HBox buttons = new HBox(10, daysComboBox, confirmOrder);
        buttons.setAlignment(Pos.CENTER);

        root.getChildren().addAll(imageView, titleText, description, buttons, daysSelectedText, backButton);
        return root;
    }
}


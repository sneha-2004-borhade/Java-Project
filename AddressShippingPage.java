
package com.home;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddressShippingPage {

    public VBox getView(Stage stage, javafx.scene.image.Image image, String title, int price, Runnable onBack) {
        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #FFF9F3;");

        Label heading = new Label("Enter Delivery Details");
        heading.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        GridPane form = new GridPane();
        form.setHgap(15);
        form.setVgap(15);
        form.setPadding(new Insets(20));
        form.setAlignment(Pos.CENTER);

        TextField nameField = new TextField();
        nameField.setPromptText("Full Name");

        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone Number");

        TextField streetField = new TextField();
        streetField.setPromptText("Street Address");

        TextField nearField = new TextField();
        nearField.setPromptText("Landmark / Nearby");

        TextField districtField = new TextField();
        districtField.setPromptText("District");

        TextField stateField = new TextField();
        stateField.setPromptText("State");

        TextField pincodeField = new TextField();
        pincodeField.setPromptText("Pin Code");

        // Layout the fields in grid
        form.add(new Label("Name:"), 0, 0);
        form.add(nameField, 1, 0);

        form.add(new Label("Phone:"), 0, 1);
        form.add(phoneField, 1, 1);

        form.add(new Label("Street:"), 0, 2);
        form.add(streetField, 1, 2);

        form.add(new Label("Landmark:"), 0, 3);
        form.add(nearField, 1, 3);

        form.add(new Label("District:"), 0, 4);
        form.add(districtField, 1, 4);

        form.add(new Label("State:"), 0, 5);
        form.add(stateField, 1, 5);

        form.add(new Label("Pincode:"), 0, 6);
        form.add(pincodeField, 1, 6);

        Button continueBtn = new Button("Confirm Address");
        continueBtn.setStyle("-fx-background-color: #DA70D6; -fx-text-fill: white; -fx-font-size: 16px;");
        continueBtn.setOnAction(e -> {
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String street = streetField.getText().trim();
            String landmark = nearField.getText().trim();
            String district = districtField.getText().trim();
            String state = stateField.getText().trim();
            String pincode = pincodeField.getText().trim();

            if (name.isEmpty() || phone.isEmpty() || street.isEmpty() || landmark.isEmpty()
                    || district.isEmpty() || state.isEmpty() || pincode.isEmpty()) {
                showAlert("All fields are required!");
            } else if (!name.matches("[a-zA-Z\\s]+")) {
                showAlert("Name must only contain letters and spaces.");
            } else if (!phone.matches("\\d{10}")) {
                showAlert("Phone number must be exactly 10 digits.");
            } else if (!pincode.matches("\\d{6}")) {
                showAlert("Pincode must be exactly 6 digits.");
            } else if (!district.matches("[a-zA-Z\\s]+")) {
                showAlert("District must only contain letters.");
            } else if (!state.matches("[a-zA-Z\\s]+")) {
                showAlert("State must only contain letters.");
            } else {
                Address addr = new Address(
                        name, phone, street, landmark, district, state, pincode
                );
                AddressManager.setAddress(addr);

                OrderConfirmedPage confirmed = new OrderConfirmedPage(stage);
                Scene scene = new Scene(confirmed.getView(onBack), 1500, 800);
                stage.setScene(scene);
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> onBack.run());
        backButton.setStyle("-fx-font-size: 14px; -fx-background-color: #C8A2C8; -fx-text-fill: white;" +
                "-fx-padding: 8 15 8 15; -fx-background-radius: 8;");
        backButton.setOnMouseEntered(e -> backButton.setStyle("-fx-background-color: #8743abff; -fx-text-fill: white; -fx-font-size: 14px;" +
                "-fx-padding: 8 15 8 15; -fx-background-radius: 8; -fx-cursor: hand;"));
        backButton.setOnMouseExited(e -> backButton.setStyle("-fx-font-size: 14px; -fx-background-color: #C8A2C8; -fx-text-fill: white;" +
                "-fx-padding: 8 15 8 15; -fx-background-radius: 8;"));

        VBox buttonBox = new VBox(10, continueBtn, backButton);
        buttonBox.setAlignment(Pos.CENTER);

        root.getChildren().addAll(heading, form, buttonBox);
        return root;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Validation Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
}

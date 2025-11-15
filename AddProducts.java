package com.home;

import java.io.File;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class AddProducts {

    private File selectedImageFile;
    private ImageView imageView;
    private VBox addedProductsContainer;

    public VBox getView(Runnable onBack) {
        Label title = new Label("Add Product");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TextField nameField = new TextField();
        nameField.setPromptText("Enter Product Name");
        nameField.setMaxWidth(400);
        nameField.setMaxHeight(180);

        TextField priceField = new TextField();
        priceField.setPromptText("Enter Price");
        priceField.setMaxWidth(400);
        priceField.setMaxHeight(180);

        TextField categoryField = new TextField();
        categoryField.setPromptText("Enter Category");
        categoryField.setMaxWidth(400);
        categoryField.setMaxHeight(180);

        TextArea descriptionField = new TextArea();
        descriptionField.setPromptText("Enter Description");
        descriptionField.setPrefRowCount(3);
        descriptionField.setMaxWidth(400);
        descriptionField.setMaxHeight(180);

        imageView = new ImageView();
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        imageView.setPreserveRatio(true);

        Button chooseImageBtn = new Button("Choose Image");
        chooseImageBtn.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Image");
            selectedImageFile = fileChooser.showOpenDialog(null);
            if (selectedImageFile != null) {
                imageView.setImage(new Image(selectedImageFile.toURI().toString()));
            }
        });

        Button saveBtn = new Button("Save Product");
        saveBtn.setStyle("-fx-background-color: #C8A3C8; -fx-text-fill: white;");
        saveBtn.setOnAction(e -> {
            String name = nameField.getText();
            String price = priceField.getText();
            String category = categoryField.getText();
            String description = descriptionField.getText();

            if (selectedImageFile == null || name.isEmpty() || price.isEmpty() || category.isEmpty() || description.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Please fill all fields and choose an image.");
                return;
            }

            try {
                String imageUrl = ImageUploader.upload(selectedImageFile);
                FireStoreUploader.saveProduct(name, price, category, description, imageUrl);
                addProductCard(name, price, category, description, selectedImageFile);
                showAlert(Alert.AlertType.INFORMATION, "Product added successfully!");

                nameField.clear();
                priceField.clear();
                categoryField.clear();
                descriptionField.clear();
                imageView.setImage(null);
                selectedImageFile = null;

            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Failed to save product: " + ex.getMessage());
            }
        });

        addedProductsContainer = new VBox(20);
        addedProductsContainer.setPadding(new Insets(10));
        addedProductsContainer.setStyle("-fx-background-color: #f5f5f5;");

        ScrollPane scrollPane = new ScrollPane(addedProductsContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(400);

        Label addedProductsTitle = new Label("Added Products");
        addedProductsTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Button backBtn = new Button("← Back");
        backBtn.setStyle("-fx-background-color: #DA70D6; -fx-text-fill: white; -fx-font-size: 16px;");
        backBtn.setOnMouseEntered(e -> backBtn.setStyle("-fx-background-color:#8743abff; -fx-text-fill: white; -fx-cursor: hand;"));
        backBtn.setOnMouseExited(e -> backBtn.setStyle("-fx-background-color: #DA70D6; -fx-text-fill: white; -fx-cursor: hand;"));
        backBtn.setOnAction(e -> onBack.run());
        HBox backContainer = new HBox(backBtn);
        backContainer.setAlignment(Pos.CENTER_LEFT);

        backBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: #5D3954;");
        backBtn.setOnAction(e -> onBack.run());

        VBox layout = new VBox(10,backContainer , title, nameField, priceField, categoryField, descriptionField,
                chooseImageBtn, imageView, saveBtn, addedProductsTitle, scrollPane);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        return layout;
    }

    private void addProductCard(String name, String price, String category, String description, File imageFile) {
        ImageView productImage = new ImageView(new Image(imageFile.toURI().toString()));
        productImage.setFitWidth(300);
        productImage.setFitHeight(300);
        productImage.setPreserveRatio(true);

        Label nameLabel = new Label("Name: " + name);
        Label priceLabel = new Label("Price: ₹" + price);
        Label categoryLabel = new Label("Category: " + category);
        Label descriptionLabel = new Label("Description: " + description);

        VBox details = new VBox(5, nameLabel, priceLabel, categoryLabel, descriptionLabel);

        HBox productCard = new HBox(10, productImage, details);
        productCard.setPadding(new Insets(10));
        productCard.setStyle("-fx-background-color: white; -fx-border-color: #ccc; -fx-border-radius: 5; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 5, 0, 2, 2);");
        productCard.setMaxWidth(400);

        addedProductsContainer.getChildren().add(productCard);
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type, message, ButtonType.OK);
        alert.showAndWait();
    }
}
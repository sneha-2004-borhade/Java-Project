package com.home;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class WishlistPage {

    public VBox getView(Runnable onBack) {
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #FFF9F3;");

        Text header = new Text("Your Wishlist");
        header.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        root.getChildren().add(header);

        for (Product product : WishlistManager.getWishlistItems()) {
            HBox itemBox = new HBox(15);
            itemBox.setAlignment(Pos.CENTER_LEFT);
            itemBox.setPadding(new Insets(10));
            itemBox.setStyle("-fx-border-color: #ddd; -fx-border-radius: 10; -fx-background-radius: 10; -fx-background-color: #ffffff;");

            ImageView imageView = new ImageView(product.getImage());
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);

            Text title = new Text(product.getTitle());

            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);

            Button removeBtn = new Button("Remove");
            removeBtn.setStyle("-fx-background-color: red; -fx-text-fill: white;");
            removeBtn.setOnMouseEntered(e -> removeBtn.setStyle("-fx-background-color: darkred; -fx-text-fill: white;"));
            removeBtn.setOnMouseExited(e -> removeBtn.setStyle("-fx-background-color: red; -fx-text-fill: white;"));

            removeBtn.setOnAction(e -> {
                WishlistManager.removeFromWishlist(product);
                VBox updatedView = getView(onBack);
                root.getChildren().setAll(updatedView.getChildren());
            });

            itemBox.getChildren().addAll(imageView, title, spacer, removeBtn);
            root.getChildren().add(itemBox);
        }

        Button backBtn = new Button("← Back");
        backBtn.setStyle("-fx-background-color: #C8A2C8; -fx-text-fill: white;");
        backBtn.setOnMouseEntered(e -> backBtn.setStyle("-fx-background-color: #A36CA8; -fx-text-fill: white;"));
        backBtn.setOnMouseExited(e -> backBtn.setStyle("-fx-background-color: #C8A2C8; -fx-text-fill: white;"));
        backBtn.setOnAction(e -> onBack.run());

        root.getChildren().add(backBtn);

        return root;
    }
}

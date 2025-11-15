
package com.home;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CartPage {

    public VBox getView(Runnable onBack) {
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #FFF9F3;");

        Text header = new Text("Your Carts");
        header.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        root.getChildren().add(header);

        for (Product product : CartManager.getCartItems()) {
            HBox itemBox = new HBox(15);
            itemBox.setAlignment(Pos.CENTER_LEFT);

            ImageView imageView = new ImageView(product.getImage());
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);

            Text title = new Text(product.getTitle());

            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);

            Button removeBtn = new Button("Remove");
            removeBtn.setOnAction(e -> {
                CartManager.removeFromCart(product);
                VBox updatedView = getView(onBack);
                root.getChildren().setAll(updatedView.getChildren());
            });
            removeBtn.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-cursor: hand;");
            removeBtn.setOnMouseEntered(e -> removeBtn.setStyle("-fx-background-color: darkred; -fx-text-fill: white; -fx-cursor: hand;"));
            removeBtn.setOnMouseExited(e -> removeBtn.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-cursor: hand;"));
            removeBtn.setAlignment(Pos.BASELINE_RIGHT);
            
            itemBox.getChildren().addAll(imageView, title,spacer, removeBtn);
            root.getChildren().add(itemBox);

        }

        Button backBtn = new Button("← Back");
        backBtn.setStyle("-fx-background-color: #DA70D6; -fx-text-fill: white; -fx-font-size: 16px;");
        backBtn.setOnMouseEntered(e -> backBtn.setStyle("-fx-background-color:#8743abff; -fx-text-fill: white; -fx-cursor: hand;"));
        backBtn.setOnMouseExited(e -> backBtn.setStyle("-fx-background-color: #DA70D6; -fx-text-fill: white; -fx-cursor: hand;"));
        backBtn.setOnAction(e -> onBack.run());
        root.getChildren().add(backBtn);

        return root;
    }
}

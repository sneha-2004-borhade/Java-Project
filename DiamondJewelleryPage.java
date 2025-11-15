package com.home;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class DiamondJewelleryPage {
    private Stage stage;

    public DiamondJewelleryPage(Stage stage) {
        this.stage = stage;
    }

    public VBox getView(Runnable onBack) {
        VBox rootVBox = new VBox(30);
        rootVBox.setPadding(new Insets(30));
        rootVBox.setStyle("-fx-background-color: #FFF9F3;");

        Button backButton = new Button("← Back");
        backButton.setOnAction(e -> onBack.run());
        backButton.setStyle("-fx-background-color: #DA70D6; -fx-text-fill: white; -fx-font-size: 16px;");
        backButton.setOnMouseEntered(e -> backButton.setStyle("-fx-background-color:#8743abff; -fx-text-fill: white; -fx-cursor: hand;"));
        backButton.setOnMouseExited(e -> backButton.setStyle("-fx-background-color: #DA70D6; -fx-text-fill: white; -fx-cursor: hand;"));
        rootVBox.getChildren().add(backButton);

        rootVBox.getChildren().addAll(
            createHBox(
                createProductCard("assets/Images/diamo.jpg", "Elegant Diamond Necklace", "₹599", stage, onBack),
                createProductCard("assets/Images/diamond.jpeg", "Diamond Bridal Set", "₹699", stage, onBack),
                createProductCard("assets/Images/diamond.jpg", "Solitaire Pendant Set", "₹549", stage, onBack)
            ),
            createHBox(
                createProductCard("assets/Images/diamond5.jpg", "Classic Diamond Set", "₹499", stage, onBack),
                createProductCard("assets/Images/diamond6.jpg", "Stylish Diamond Earrings", "₹459", stage, onBack),
                createProductCard("assets/Images/diamond7.jpg", "Designer Diamond Necklace", "₹399", stage, onBack)
            ),
            createHBox(
                createProductCard("assets/Images/diamond8.jpg", "Elegant Diamond Necklace", "₹599", stage, onBack),
                createProductCard("assets/Images/diamond9.jpg", "Diamond Bridal Set", "₹699", stage, onBack),
                createProductCard("assets/Images/diamond10.jpg", "Solitaire Pendant Set", "₹549", stage, onBack)
            ),
            createHBox(
                createProductCard("assets/Images/diamond11.jpg", "Classic Diamond Set", "₹499", stage, onBack),
                createProductCard("assets/Images/diamond12.jpg", "Stylish Diamond Set", "₹459", stage, onBack),
                createProductCard("assets/Images/diamond13.jpg", "Designer Diamond Set", "₹399", stage, onBack)
            ),
            createHBox(
                createProductCard("assets/Images/diamond14.jpg", "Classic Diamond Set", "₹499", stage, onBack),
                createProductCard("assets/Images/diamond15.jpg", "Stylish Diamond Set", "₹459", stage, onBack),
                createProductCard("assets/Images/diamond8.jpg", "Designer Diamond Set", "₹399", stage, onBack)
            )
        );

        ScrollPane scrollPane = new ScrollPane(rootVBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #FFF9F3;");

        VBox layout = new VBox(scrollPane);
        return layout;
    }

    private HBox createHBox(VBox... cards) {
        HBox hbox = new HBox(25);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(cards);
        return hbox;
    }

    private VBox createProductCard(String imagePath, String title, String rentPerDay, Stage stage, Runnable onBack) {
        VBox card = new VBox(10);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(10));
        card.setStyle("-fx-background-color: white; -fx-border-color: #e0e0e0; -fx-border-width: 1px;");
        card.setPrefWidth(300);

        Image image = new Image(imagePath, 200, 250, true, true);
        ImageView imageView = new ImageView(image);

        Button heartBtn = new Button("♥");
        heartBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: red; -fx-font-size: 16;");
        heartBtn.setOnAction(e -> WishlistManager.addToWishlist(new Product(image, title)));
        heartBtn.setOnMouseEntered(e -> heartBtn.setStyle("-fx-background-color: darkred; -fx-text-fill: white; -fx-cursor: hand;"));
        heartBtn.setOnMouseExited(e -> heartBtn.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-cursor: hand;"));

        StackPane imageContainer = new StackPane(imageView, heartBtn);
        StackPane.setAlignment(heartBtn, Pos.TOP_RIGHT);

        Text titleText = new Text(title);
        titleText.setFont(Font.font("Georgia", 14));
        titleText.setFill(Color.BLACK);
        titleText.setWrappingWidth(260);
        titleText.setTextAlignment(TextAlignment.CENTER);

        Text rentText = new Text("Rent per Day: " + rentPerDay);
        rentText.setFont(Font.font("Georgia", 13));
        rentText.setFill(Color.GRAY);

        Button rentNow = new Button("Rent Now");
        rentNow.setStyle("-fx-font-size: 14px; -fx-background-color: #C8A2C8; -fx-text-fill: white;" +
                "-fx-padding: 8 15 8 15; -fx-background-radius: 8;");
        rentNow.setOnMouseEntered(e -> rentNow.setStyle("-fx-background-color:#8743abff; -fx-text-fill: white; -fx-cursor: hand;"));
        rentNow.setOnMouseExited(e -> rentNow.setStyle("-fx-background-color: #DA70D6; -fx-text-fill: white; -fx-cursor: hand;"));
        rentNow.setOnAction(e -> {
            ProductDescriptionPage descPage = new ProductDescriptionPage();
            Scene scene = new Scene(descPage.getView(stage, image, title, onBack), 1500, 800);
            stage.setScene(scene);
        });

        Button addToCart = new Button("Add to Cart");
        addToCart.setOnAction(e -> CartManager.addToCart(new Product(image,title)));
        addToCart.setStyle("-fx-background-color: #FF69B4; -fx-text-fill: white;");
        addToCart.setOnMouseEntered(e -> addToCart.setStyle("-fx-background-color: #9d0f56ff; -fx-text-fill: white; -fx-cursor: hand;"));
        addToCart.setOnMouseExited(e -> addToCart.setStyle("-fx-background-color: #FF69B4; -fx-text-fill: white; -fx-cursor: hand;"));

        HBox actionButtons = new HBox(10, rentNow, addToCart);
        actionButtons.setAlignment(Pos.CENTER);

        card.getChildren().addAll(imageContainer, titleText, rentText, actionButtons);
        return card;
    }
}

package com.home;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class GoldenJewelleryPage {
    private Stage stage;

    public GoldenJewelleryPage(Stage stage) {
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
                        createJewelleryCard("assets/Images/gold.jpg", "Traditional Gold Necklace\na piece of jewelry made of gold that we wear around the neck", "₹599", stage, onBack),
                        createJewelleryCard("assets/Images/gold.jpeg", "Classic Gold Bangles", "₹399", stage, onBack),
                        createJewelleryCard("assets/Images/gold27.jpg", "Designer Gold Earrings", "₹149", stage, onBack)
                ),
                createHBox(
                        createJewelleryCard("assets/Images/gold4.jpg", "Gold Chocker", "₹259", stage, onBack),
                        createJewelleryCard("assets/Images/gold5.jpg", "Bridal Gold Set", "₹299", stage, onBack),
                        createJewelleryCard("assets/Images/gold6.jpg", "Antique Gold Choker", "₹749", stage, onBack)
                ),
                createHBox(
                        createJewelleryCard("assets/Images/gold3.jpg", "Gold Maang Tikka", "₹259", stage, onBack),
                        createJewelleryCard("assets/Images/gold7.jpg", "Bridal Gold Set", "₹999", stage, onBack),
                        createJewelleryCard("assets/Images/gold8.jpg", "Antique Gold Choker", "₹749", stage, onBack)
                ),createHBox(
                        createJewelleryCard("assets/Images/gold9.jpg", "Gold Bridal Set", "₹259", stage, onBack),
                        createJewelleryCard("assets/Images/gold10.jpg", "Bridal Gold NosePin", "₹99", stage, onBack),
                        createJewelleryCard("assets/Images/gold11.jpg", "Antique Gold Choker", "₹749", stage, onBack)
                ),
                createHBox(
                        createJewelleryCard("assets/Images/gold12.jpg", "Gold Maang Tikka", "₹259", stage, onBack),
                        createJewelleryCard("assets/Images/gold13.jpg", "Bridal Gold Set", "₹999", stage, onBack),
                        createJewelleryCard("assets/Images/gold14.jpg", "Antique Gold Choker", "₹749", stage, onBack)
                ),
                createHBox(
                        createJewelleryCard("assets/Images/gold15.jpg", "Gold Maang Tikka", "₹259", stage, onBack),
                        createJewelleryCard("assets/Images/gold16.jpg", "Bridal Gold Set", "₹999", stage, onBack),
                        createJewelleryCard("assets/Images/gold17.jpg", "Antique Gold WaistBelt", "₹349", stage, onBack)
                ),
                createHBox(
                        createJewelleryCard("assets/Images/gold18.jpg", "Gold Bridal WaistBelt", "₹259", stage, onBack),
                        createJewelleryCard("assets/Images/gold19.jpg", "Bridal Gold WaistBelt", "₹599", stage, onBack),
                        createJewelleryCard("assets/Images/gold20.jpg", "Pearl-Golden Ear-Rings", "₹149", stage, onBack)
                ),
                createHBox(
                        createJewelleryCard("assets/Images/gold21.jpg", "Gold Set", "₹259", stage, onBack),
                        createJewelleryCard("assets/Images/gold22.jpg", "Bridal Gold Set", "₹999", stage, onBack),
                        createJewelleryCard("assets/Images/gold23.jpg", "Antique Gold Choker", "₹749", stage, onBack)
                ),
                createHBox(
                        createJewelleryCard("assets/Images/gold24.jpg", "Gold Set", "₹259", stage, onBack),
                        createJewelleryCard("assets/Images/gold25.jpg", "Bridal Gold Set", "₹999", stage, onBack),
                        createJewelleryCard("assets/Images/gold26.jpg", "Antique Gold EarRings", "₹249", stage, onBack)
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

    private VBox createJewelleryCard(String imagePath, String title, String rentPerDay, Stage stage, Runnable onBack) {
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
        addToCart.setOnAction(e -> CartManager.addToCart(new Product(image, title)));
        addToCart.setStyle("-fx-background-color: #FF69B4; -fx-text-fill: white;");
        addToCart.setOnMouseEntered(e -> addToCart.setStyle("-fx-background-color: #9d0f56ff; -fx-text-fill: white; -fx-cursor: hand;"));
        addToCart.setOnMouseExited(e -> addToCart.setStyle("-fx-background-color: #FF69B4; -fx-text-fill: white; -fx-cursor: hand;"));

        HBox actionButtons = new HBox(10, rentNow, addToCart);
        actionButtons.setAlignment(Pos.CENTER);

        card.getChildren().addAll(imageContainer, titleText, rentText, actionButtons);
        return card;
    }
}

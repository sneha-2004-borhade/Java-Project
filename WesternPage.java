package com.home;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class WesternPage {

    private final String FIREBASE_PROJECT_URL = "https://firestore.googleapis.com/v1/projects/glam-100a6/databases/(default)/documents/products";
    private final String BUCKET_URL = "https://firebasestorage.googleapis.com/v0/b/glam-100a6/o/";
    private Stage stage;
    private final List<VBox> allProductCards = new ArrayList<>();

    public WesternPage(Stage stage) {
        this.stage = stage;
    }

    public VBox getView(Runnable onBack) {
        VBox rootVBox = new VBox(30);
        rootVBox.setPadding(new Insets(30));
        rootVBox.setStyle("-fx-background-color: #FFF9F3;");

        Button backButton = new Button("← Back");
        backButton.setOnAction(e -> onBack.run());
        backButton.setStyle("-fx-font-size: 14px; -fx-background-color: #C8A2C8; -fx-text-fill: white;" +
                "-fx-padding: 8 15 8 15; -fx-background-radius: 8;");
        backButton.setOnMouseEntered(e -> backButton.setStyle("-fx-background-color:#8743abff; -fx-text-fill: white; -fx-cursor: hand;"));
        backButton.setOnMouseExited(e -> backButton.setStyle("-fx-background-color: #DA70D6; -fx-text-fill: white; -fx-cursor: hand;"));

        TextField searchField = new TextField();
        searchField.setPromptText("Search sarees...");
        searchField.setPrefWidth(250);
        searchField.setStyle("-fx-font-size: 14px;");

        Button searchButton = new Button("Search");
        searchButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        searchButton.setOnMouseEntered(e -> searchButton.setStyle("-fx-background-color: darkgray; -fx-text-fill: white; -fx-cursor: hand;"));
        searchButton.setOnMouseExited(e -> searchButton.setStyle("-fx-background-color: black; -fx-text-fill: white;"));

        HBox topBar = new HBox(20, backButton, searchField, searchButton);
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPadding(new Insets(10));

        rootVBox.getChildren().add(topBar);

        VBox productContainer = new VBox(30);
        rootVBox.getChildren().add(productContainer);

        searchButton.setOnAction(e -> {
            String query = searchField.getText().toLowerCase().trim();
            productContainer.getChildren().clear();

            boolean found = false;
            for (VBox productCard : allProductCards) {
                for (javafx.scene.Node node : productCard.getChildren()) {
                    if (node instanceof Text text && text.getText().toLowerCase().contains(query)) {
                        productContainer.getChildren().add(productCard);
                        found = true;
                        break;
                    }
                }
            }

            if (!found) {
                Text noResults = new Text("No matching sarees found.");
                noResults.setFill(Color.RED);
                noResults.setFont(Font.font(16));
                productContainer.getChildren().add(noResults);
            }
        });

        fetchAndDisplayProducts(productContainer);

        ScrollPane scrollPane = new ScrollPane(rootVBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #FFF9F3;");

        VBox finalLayout = new VBox(scrollPane);
        finalLayout.setPrefSize(1200, 800);
        return finalLayout;
    }

    private VBox createWesternCard(String firebaseImagePath, String title, String rentPerDay, String category,String description,Stage stage, Runnable onBack) {
        VBox card = new VBox(10);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(10));
        card.setStyle("-fx-background-color: white; -fx-border-color: #e0e0e0; -fx-border-width: 1px;");
        card.setPrefWidth(300);

        ImageView imageView = new ImageView();
        imageView.setFitWidth(200);
        imageView.setFitHeight(250);
        imageView.setPreserveRatio(true);

        Button heartBtn = new Button("♥");
        heartBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: red; -fx-font-size: 16;");
        heartBtn.setOnAction(e -> WishlistManager.addToWishlist(new Product(imageView.getImage(), title)));
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
            Scene scene = new Scene(descPage.getView(stage, imageView.getImage(), title, onBack), 1500, 800);
            stage.setScene(scene);
        });

        Button addToCart = new Button("Add to Cart");
        addToCart.setStyle("-fx-background-color: #FF69B4; -fx-text-fill: white;");
        addToCart.setOnMouseEntered(e -> addToCart.setStyle("-fx-background-color: #9d0f56ff; -fx-text-fill: white; -fx-cursor: hand;"));
        addToCart.setOnMouseExited(e -> addToCart.setStyle("-fx-background-color: #FF69B4; -fx-text-fill: white; -fx-cursor: hand;"));
        addToCart.setOnAction(e -> CartManager.addToCart(new Product(imageView.getImage(), title)));

        HBox actionButtons = new HBox(10, rentNow, addToCart);
        actionButtons.setAlignment(Pos.CENTER);

        card.getChildren().addAll(imageContainer, titleText, rentText, actionButtons);
        return card;
    }

    // private void fetchAndDisplayProducts(VBox container) {
    //     try {
    //         URL url = new URL(FIREBASE_PROJECT_URL);
    //         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    //         conn.setRequestMethod("GET");

    //         Scanner sc = new Scanner(conn.getInputStream());
    //         StringBuilder response = new StringBuilder();
    //         while (sc.hasNext()) {
    //             response.append(sc.nextLine());
    //         }
    //         sc.close();

    //         JSONObject json = new JSONObject(response.toString());

    //         if (!json.has("documents")) {
    //             System.out.println("No documents found in Firestore.");
    //             return;
    //         }

    //         JSONArray documents = json.getJSONArray("documents");

    //         for (int i = 0; i < documents.length(); i++) {
    //             JSONObject doc = documents.getJSONObject(i);
    //             JSONObject fields = doc.getJSONObject("fields");

    //             String name = fields.getJSONObject("name").getString("stringValue");
    //             String price = fields.getJSONObject("price").getString("stringValue");
    //             String category = fields.getJSONObject("category").getString("stringValue");
    //             String description = fields.getJSONObject("description").getString("stringValue");

    //             String fullUrl = fields.getJSONObject("imageUrl").getString("stringValue");

    //             String imagePath;
    //             if (fullUrl.contains("/o/")) {
    //                 int start = fullUrl.indexOf("/o/") + 3;
    //                 int end = fullUrl.indexOf("?alt=");
    //                 imagePath = URLDecoder.decode(fullUrl.substring(start, end), "UTF-8");
    //             } else {
    //                 imagePath = fullUrl;
    //             }

    //             String finalImageUrl = BUCKET_URL + URLEncoder.encode(imagePath, "UTF-8") + "?alt=media";

    //             VBox productCard = createWesternCard(imagePath, name, price,category,description, stage, () -> getView(() -> {}));

    //             ImageView productImage = (ImageView) ((StackPane) productCard.getChildren().get(0)).getChildren().get(0);
    //             try {
    //                 Image image = new Image(finalImageUrl, true);
    //                 productImage.setImage(image);
    //             } catch (Exception e) {
    //                 System.out.println("Failed to load image: " + e.getMessage());
    //             }

    //             allProductCards.add(productCard);
    //             container.getChildren().add(productCard);
    //         }

    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    // ... तुझं बाकी सगळं code जसंच्या तसं ...

private void fetchAndDisplayProducts(VBox container) {
    try {
        URL url = new URL(FIREBASE_PROJECT_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        Scanner sc = new Scanner(conn.getInputStream());
        StringBuilder response = new StringBuilder();
        while (sc.hasNext()) {
            response.append(sc.nextLine());
        }
        sc.close();

        JSONObject json = new JSONObject(response.toString());

        if (!json.has("documents")) {
            System.out.println("No documents found in Firestore.");
            return;
        }

        JSONArray documents = json.getJSONArray("documents");

        List<VBox> rowCards = new ArrayList<>(); // ✅ add this line

        for (int i = 0; i < documents.length(); i++) {
            JSONObject doc = documents.getJSONObject(i);
            JSONObject fields = doc.getJSONObject("fields");

            String name = fields.getJSONObject("name").getString("stringValue");
            String price = fields.getJSONObject("price").getString("stringValue");
            String category = fields.getJSONObject("category").getString("stringValue");
            String description = fields.getJSONObject("description").getString("stringValue");

            String fullUrl = fields.getJSONObject("imageUrl").getString("stringValue");

            String imagePath;
            if (fullUrl.contains("/o/")) {
                int start = fullUrl.indexOf("/o/") + 3;
                int end = fullUrl.indexOf("?alt=");
                imagePath = URLDecoder.decode(fullUrl.substring(start, end), "UTF-8");
            } else {
                imagePath = fullUrl;
            }

            String finalImageUrl = BUCKET_URL + URLEncoder.encode(imagePath, "UTF-8") + "?alt=media";

            VBox productCard = createWesternCard(imagePath, name, price, category, description, stage, () -> getView(() -> {}));

            ImageView productImage = (ImageView) ((StackPane) productCard.getChildren().get(0)).getChildren().get(0);
            try {
                Image image = new Image(finalImageUrl, true);
                productImage.setImage(image);
            } catch (Exception e) {
                System.out.println("Failed to load image: " + e.getMessage());
            }

            allProductCards.add(productCard);

            // ✅ instead of directly adding, group in HBoxes of 3
            rowCards.add(productCard);
            if (rowCards.size() == 3) {
                HBox row = new HBox(25);
                row.setAlignment(Pos.CENTER);
                row.getChildren().addAll(rowCards);
                container.getChildren().add(row);
                rowCards.clear();
            }
        }

        // ✅ add remaining cards if any (less than 3 in last row)
        if (!rowCards.isEmpty()) {
            HBox row = new HBox(25);
            row.setAlignment(Pos.CENTER);
            row.getChildren().addAll(rowCards);
            container.getChildren().add(row);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}

}



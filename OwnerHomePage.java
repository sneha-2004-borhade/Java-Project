package com.home;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class OwnerHomePage {

    public void start(Stage stage) {
        JSONArray documents = fetchProductsFromFirestore();

        ImageView profileImage = new ImageView(new Image("assets/images/ownerp.jpg"));
        profileImage.setFitWidth(40);
        profileImage.setFitHeight(40);
        profileImage.setPreserveRatio(true);
        profileImage.setSmooth(true);
        profileImage.setStyle("-fx-cursor: hand;");
        profileImage.setOnMouseClicked(event -> {
            OwnerIcon ownerIconPage = new OwnerIcon();
            VBox view = ownerIconPage.getView(stage, () -> start(stage));
            Scene scene = new Scene(view, 1500, 800);
            stage.setScene(scene);
        });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Text text = new Text("GlamOnRent");
        text.setFont(Font.font("Georgia", FontWeight.BOLD, 30));

        HBox appBar = new HBox(10, text, spacer, profileImage);
        appBar.setPadding(new Insets(10));
        appBar.setAlignment(Pos.CENTER_LEFT);
        appBar.setStyle("-fx-background-color: #c8a2c8; -fx-background-radius: 30;");
        appBar.setEffect(new DropShadow(6, Color.rgb(200, 120, 160, 0.3)));

        Button btn1 = new Button("Add Products");
        btn1.setOnAction(e -> {
            AddProducts addProductPage = new AddProducts();
            VBox addProductView = addProductPage.getView(() -> start(stage));
            Scene addProductScene = new Scene(addProductView, 1500, 800);
            stage.setScene(addProductScene);
        });

       
        Button btn2 = new Button("Accepted Bookings");
btn2.setOnAction(e -> {
    AcceptedBookingsPage acceptedPage = new AcceptedBookingsPage();

    Scene acceptedScene = new Scene(acceptedPage.getView(() -> {
        // Back button clicked → go back to owner home page
        start(stage);
    }), 1500, 800);

    stage.setScene(acceptedScene);
});
        ReportsPage reportsPage = new ReportsPage();
         Button btn3 = new Button("Reports");
        btn3.setOnAction(e -> {
            VBox view = reportsPage.getView(() -> start(stage));
            Scene reportsScene = new Scene(view, 1500, 800);
            stage.setScene(reportsScene);
        });


        Button btn4 = new Button("View Booking Requests");
        btn4.setOnAction(e -> {
            BookingRequestsPage bookingPage = new BookingRequestsPage();
            ScrollPane bookingView = bookingPage.getView(() -> start(stage));
            Scene bookingScene = new Scene(bookingView, 1500, 800);
            stage.setScene(bookingScene);
        });

       
        Button[] allButtons = {btn1,btn2,btn3, btn4};
        for (Button btn : allButtons) {
            btn.setPrefWidth(200);
            btn.setPrefHeight(45);
        }

        HBox buttonBox = new HBox(20, btn1,btn2,btn3, btn4);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(20, 0, 10, 0));

        Label heading = new Label("Recently Added");
        heading.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #3B2C35;");
        heading.setPadding(new Insets(20, 0, 10, 10));

        VBox productGrid = new VBox(30);
        int itemsPerRow = 3;
        HBox currentRow = new HBox(25);
        currentRow.setAlignment(Pos.CENTER);

        for (int i = 0; i < documents.length(); i++) {
            JSONObject doc = documents.getJSONObject(i);
            JSONObject fields = doc.getJSONObject("fields");

            String fullUrl = fields.getJSONObject("imageUrl").getString("stringValue");
String imagePath;
String finalImageUrl = "";

try {
    if (fullUrl.contains("/o/")) {
        int start = fullUrl.indexOf("/o/") + 3;
        int end = fullUrl.indexOf("?alt=");
        imagePath = URLDecoder.decode(fullUrl.substring(start, end), "UTF-8");
    } else {
        imagePath = fullUrl;
    }

    finalImageUrl = "https://firebasestorage.googleapis.com/v0/b/glam-100a6/o/"
            + URLEncoder.encode(imagePath, "UTF-8") + "?alt=media";
} catch (Exception e) {
    System.out.println("🔥 Error processing image URL: " + e.getMessage());
    imagePath = fullUrl; // fallback
    finalImageUrl = imagePath; // fallback
}


            String title = fields.getJSONObject("name").getString("stringValue");
            String rent = fields.getJSONObject("price").getString("stringValue");

            VBox card = createSareeCard(finalImageUrl, title, "Rent Per Day : ₹" + rent);
            currentRow.getChildren().add(card);

            if ((i + 1) % itemsPerRow == 0) {
                productGrid.getChildren().add(currentRow);
                currentRow = new HBox(25);
                currentRow.setAlignment(Pos.CENTER);
            }
        }

        if (!currentRow.getChildren().isEmpty()) {
            productGrid.getChildren().add(currentRow);
        }

        VBox content = new VBox(buttonBox, heading, productGrid);
        content.setStyle("-fx-background-color: #FFF9F3;");
        content.setPadding(new Insets(10));

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: white;");
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        VBox rootVBox = new VBox(appBar, scrollPane);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        Scene scene = new Scene(rootVBox, 1500, 800);
        stage.setScene(scene);
        stage.setTitle("Glam On Rent");
        stage.show();
    }

    private VBox createSareeCard(String finalImageUrl, String title, String rentPerDay) {
        VBox card = new VBox(10);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(10));
        card.setStyle("-fx-background-color: white; -fx-background-radius: 20;"
                + "-fx-effect: dropshadow(three-pass-box, rgba(237, 232, 236, 1), 10, 0, 0, 5);");
        card.setPrefWidth(300);

        ImageView imageView = new ImageView(new Image(finalImageUrl, true));
        imageView.setFitWidth(300);
        imageView.setFitHeight(350);
        imageView.setPreserveRatio(false);

        Label titleLabel = new Label(title);
        titleLabel.setWrapText(true);
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        Label priceLabel = new Label(rentPerDay);
        priceLabel.setStyle("-fx-text-fill: #6c6269ff; -fx-font-size: 16px;");

        card.getChildren().addAll(imageView, titleLabel, priceLabel);
        return card;
    }

    private static JSONArray fetchProductsFromFirestore() {
        try {
            URL url = new URL("https://firestore.googleapis.com/v1/projects/glam-100a6/databases/(default)/documents/products");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                response.append(line);
            }

            br.close();
            conn.disconnect();

            JSONObject json = new JSONObject(response.toString());
            return json.getJSONArray("documents");
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }
}

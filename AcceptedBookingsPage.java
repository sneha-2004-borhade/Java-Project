package com.home;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AcceptedBookingsPage {

    public ScrollPane getView(Runnable onBack) {
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color:  #FFF7F0;");

        Label heading = new Label("Accepted Bookings");
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        heading.setStyle("-fx-text-fill: #550452ff;");

        Button backBtn = new Button("← Back");
        backBtn.setStyle("-fx-background-color:#DA70D6; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 6 20 6 20; -fx-background-radius: 12;");
        backBtn.setAlignment(Pos.CENTER_LEFT);
        backBtn.setOnAction(e -> onBack.run());

        VBox allOrdersBox = new VBox(30);
        allOrdersBox.setAlignment(Pos.TOP_CENTER);

        JSONArray orders = fetchOrdersFromFirestore();
        List<VBox> orderCards = new ArrayList<>();

        for (int i = 0; i < orders.length(); i++) {
            JSONObject doc = orders.getJSONObject(i);
            JSONObject fields = doc.getJSONObject("fields");

            String status = getString(fields, "status");
            if (!"completed".equalsIgnoreCase(status)) continue;

            List<VBox> cards = createOrderBoxes(doc);
            orderCards.addAll(cards);
        }

        HBox currentRow = new HBox(30);
        currentRow.setAlignment(Pos.TOP_CENTER);
        int count = 0;

        for (VBox card : orderCards) {
            currentRow.getChildren().add(card);
            count++;
            if (count % 2 == 0) {
                allOrdersBox.getChildren().add(currentRow);
                currentRow = new HBox(30);
                currentRow.setAlignment(Pos.TOP_CENTER);
            }
        }

        if (!currentRow.getChildren().isEmpty()) {
            allOrdersBox.getChildren().add(currentRow);
        }

        root.getChildren().addAll(backBtn, heading, allOrdersBox);

        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #FFF7F0;");
        return scrollPane;
    }

    private List<VBox> createOrderBoxes(JSONObject doc) {
        JSONObject fields = doc.getJSONObject("fields");

        String name = getString(fields, "name");
        String phone = getString(fields, "phone");
        String street = getString(fields, "street");
        String district = getString(fields, "district");
        String landmark = getString(fields, "landmark");
        String pincode = getString(fields, "pincode");
        String state = getString(fields, "state");

        JSONArray products = new JSONArray();
        try {
            JSONObject productArray = fields.getJSONObject("products").getJSONObject("arrayValue");
            if (productArray.has("values")) {
                products = productArray.getJSONArray("values");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<VBox> productCards = new ArrayList<>();

        for (int i = 0; i < products.length(); i++) {
            JSONObject productFields = products.getJSONObject(i).getJSONObject("mapValue").getJSONObject("fields");

            String title = getString(productFields, "title");
            String imageUrl = getString(productFields, "image");
            String days = getString(productFields, "days");
            String amountStr = getString(productFields, "amount");

            VBox card = new VBox(10);
            card.setPadding(new Insets(12));
            card.setPrefWidth(300);
            card.setStyle("-fx-background-color: white; -fx-background-radius: 12; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.08), 8, 0, 0, 3);");
            card.setAlignment(Pos.TOP_CENTER);

            ImageView imageView = new ImageView(new Image(imageUrl));
            imageView.setFitWidth(280);
            imageView.setFitHeight(200);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);

            HBox image = new HBox(imageView);
            image.setAlignment(Pos.CENTER);

            Label titleLabel = new Label(title + " (" + days + " days)");
            titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

            Label nameLabel = new Label("👤 Name: " + name);
            Label phoneLabel = new Label("📞 Phone: " + phone);
            Label addressLabel = new Label("📍 Address: " + street + ", " + landmark + ", " + district + ", " + state + " - " + pincode);
            Label amountLabel = new Label("💰 Amount: ₹" + amountStr);

            nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
            phoneLabel.setFont(Font.font("Arial", 12));
            addressLabel.setFont(Font.font("Arial", 12));
            amountLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
            addressLabel.setWrapText(true);

            card.getChildren().addAll(image, titleLabel, nameLabel, phoneLabel, addressLabel, amountLabel);
            card.setAlignment(Pos.CENTER_LEFT);
            productCards.add(card);
        }

        return productCards;
    }

    private String getString(JSONObject fields, String key) {
        if (fields.has(key)) {
            JSONObject field = fields.getJSONObject(key);
            if (field.has("stringValue")) {
                return field.getString("stringValue");
            } else if (field.has("integerValue")) {
                return field.getString("integerValue");
            } else if (field.has("doubleValue")) {
                return field.getString("doubleValue");
            }
        }
        return "";
    }

    private JSONArray fetchOrdersFromFirestore() {
        try {
            URL url = new URL("https://firestore.googleapis.com/v1/projects/glam-100a6/databases/(default)/documents/orders");
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

            if (json.has("documents")) {
                return json.getJSONArray("documents");
            } else {
                System.out.println("⚠️ No documents found in Firebase response.");
                return new JSONArray();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }
}

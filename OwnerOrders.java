package com.home;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OwnerOrders {

    public ScrollPane getView(Runnable onBack) {
        VBox root = new VBox(30);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: #FFF9F3;");

        Label title = new Label("Customer Orders");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        title.setStyle("-fx-text-fill: #3B2C35;");

        VBox ordersGrid = new VBox(30);
        ordersGrid.setAlignment(Pos.TOP_CENTER);

        JSONArray documents = fetchOrdersFromFirestore();

        for (int i = 0; i < documents.length(); i++) {
            JSONObject doc = documents.getJSONObject(i);
            JSONObject fields = doc.getJSONObject("fields");

            String name = getString(fields, "name");
            String phone = getString(fields, "phone");

            JSONArray products = fields.getJSONObject("products")
                    .getJSONObject("arrayValue")
                    .getJSONArray("values");

            for (int j = 0; j < products.length(); j++) {
                JSONObject productFields = products.getJSONObject(j)
                        .getJSONObject("mapValue")
                        .getJSONObject("fields");

                String titleText = getString(productFields, "title");
                String imagePath = getString(productFields, "image");
                String days = getString(productFields, "days");

                VBox orderBox = createOrderBox("Order", name, titleText + " (" + days + " days)", "Pending", imagePath);
                ordersGrid.getChildren().add(orderBox);
            }
        }

        Button backBtn = new Button("← Back");
        backBtn.setStyle("-fx-background-color: #C8A2C8; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 8 20 8 20; -fx-background-radius: 12;");
        backBtn.setOnAction(e -> onBack.run());

        root.getChildren().addAll(title, ordersGrid, backBtn);

        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #FFF9F3; -fx-border-color: transparent;");
        return scrollPane;
    }

    private VBox createOrderBox(String orderId, String customer, String product, String statusText, String imagePath) {
        VBox box = new VBox(10);
        box.setPadding(new Insets(15));
        box.setStyle(
            "-fx-background-color: #ffffff;" +
            "-fx-border-radius: 15;" +
            "-fx-background-radius: 15;" +
            "-fx-effect: dropshadow(three-pass-box, rgba(93, 57, 84, 0.15), 10, 0, 0, 4);"
        );
        box.setPrefWidth(280);
        box.setAlignment(Pos.TOP_CENTER);

        ImageView productImage = new ImageView(new Image(
                imagePath.startsWith("http") ? imagePath :
                getClass().getResource("/assets/images/default.png").toExternalForm()
        ));
        productImage.setFitWidth(220);
        productImage.setFitHeight(150);
        productImage.setPreserveRatio(true);

        Label productLabel = new Label(product);
        productLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        productLabel.setStyle("-fx-text-fill: #5D3954;");

        Label customerLabel = new Label("Customer: " + customer);
        Label orderIdLabel = new Label("Order ID: " + orderId);
        Label paymentLabel = new Label("Payment: ₹2000");

        Label status = new Label("Status: " + statusText);
        status.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        status.setStyle("-fx-text-fill: #5D3954;");

        HBox btnRow = new HBox(8);
        btnRow.setAlignment(Pos.CENTER);

        Button approveBtn = new Button("Approve");
        Button rejectBtn = new Button("Reject");
        Button returnBtn = new Button("Returned");

        approveBtn.setStyle("-fx-background-color: #6ab04c; -fx-text-fill: white;");
        rejectBtn.setStyle("-fx-background-color: #eb4d4b; -fx-text-fill: white;");
        returnBtn.setStyle("-fx-background-color: #f0932b; -fx-text-fill: white;");

        approveBtn.setOnAction(e -> status.setText("Status: Approved"));
        rejectBtn.setOnAction(e -> status.setText("Status: Rejected"));
        returnBtn.setOnAction(e -> status.setText("Status: Returned"));

        btnRow.getChildren().addAll(approveBtn, rejectBtn, returnBtn);

        box.getChildren().addAll(productImage, productLabel, orderIdLabel, customerLabel, paymentLabel, status, btnRow);
        return box;
    }

    private static JSONArray fetchOrdersFromFirestore() {
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
            return json.getJSONArray("documents");
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }

    private static String getString(JSONObject fields, String key) {
        if (fields.has(key)) {
            JSONObject field = fields.getJSONObject(key);
            if (field.has("stringValue")) {
                return field.getString("stringValue");
            } else if (field.has("integerValue")) {
                return field.getString("integerValue");
            }
        }
        return "";
    }
}

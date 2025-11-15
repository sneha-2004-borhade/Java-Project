


package com.home;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class OrdersPage {

    private final String FIREBASE_URL = "https://firestore.googleapis.com/v1/projects/glam-100a6/databases/(default)/documents/orders";

    public VBox getView(Runnable onBack) {
        VBox content = new VBox(30);
        content.setPadding(new Insets(20));
        content.setAlignment(Pos.TOP_CENTER);
        content.setStyle("-fx-background-color: #fefefe;");

        Text header = new Text("Your Orders");
        header.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-fill: #333;");
        content.getChildren().add(header);

        Address address = AddressManager.getAddress();

        JSONArray productListJson = new JSONArray();
        int totalAmount = 0;

        // ✅ UI Loop – सर्व products साठी cards तयार कर
        for (Product product : OrderManager.getOrders()) {
            VBox card = new VBox(15);
            card.setAlignment(Pos.TOP_CENTER);
            card.setPadding(new Insets(20));
            card.setSpacing(10);
            card.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 12px; -fx-effect: dropshadow(two-pass-box, rgba(0,0,0,0.1), 6, 0, 0, 2);");
            card.setMaxWidth(600);

            ImageView imageView = new ImageView(product.getImage());
            imageView.setFitWidth(400);
            imageView.setFitHeight(400);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            card.getChildren().add(imageView);

            Text titleText = new Text(product.getTitle());
            titleText.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

            Text daysText = new Text("Rented for " + product.getDays() + " day(s)");
            daysText.setStyle("-fx-font-size: 14px; -fx-fill: #666;");

            VBox detailsBox = new VBox(10);
            detailsBox.setAlignment(Pos.CENTER);
            detailsBox.getChildren().addAll(titleText, daysText);

            card.getChildren().add(detailsBox);
            content.getChildren().add(card);
        }

        // ✅ Firebase Loop – फक्त नवीन products पाठव
        for (Product product : OrderManager.getOrders()) {
            if (!product.isSyncedToFirebase()) {
                int productAmount = product.getDays() * 299;

                JSONObject productJson = new JSONObject();
                productJson.put("title", product.getTitle());
                productJson.put("days", product.getDays());
                productJson.put("amount", productAmount);
                String imageUrl = product.getImage().getUrl();
                productJson.put("image", imageUrl != null ? imageUrl : "");

                productListJson.put(productJson);
                totalAmount += productAmount;

                product.setSyncedToFirebase(true); // ✅ mark as synced
            }
        }

        // ✅ Order fire करायचा का check कर
        if (address != null && productListJson.length() > 0) {
            JSONObject orderData = new JSONObject();
            orderData.put("name", address.getName());
            orderData.put("phone", address.getPhone());
            orderData.put("street", address.getStreet());
            orderData.put("landmark", address.getLandmark());
            orderData.put("district", address.getDistrict());
            orderData.put("state", address.getState());
            orderData.put("pincode", address.getPincode());
            orderData.put("products", productListJson);
            orderData.put("amount", totalAmount);
            orderData.put("payment", "paid");
            orderData.put("status", "pending");

            ReportController.incrementPendingBooking();
            sendOrderToFirebase(orderData);
        }

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> onBack.run());
        backButton.setStyle("-fx-background-color: #DA70D6; -fx-text-fill: white; -fx-font-size: 16px;");
        backButton.setOnMouseEntered(e -> backButton.setStyle("-fx-background-color:#8743abff; -fx-text-fill: white; -fx-cursor: hand;"));
        backButton.setOnMouseExited(e -> backButton.setStyle("-fx-background-color: #DA70D6; -fx-text-fill: white; -fx-cursor: hand;"));

        content.getChildren().add(backButton);

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent;");
        scrollPane.setPadding(new Insets(10));

        VBox wrapper = new VBox(scrollPane);
        wrapper.setAlignment(Pos.CENTER);
        return wrapper;
    }

    private void sendOrderToFirebase(JSONObject orderData) {
        JSONObject firestoreFormattedData = convertToFirestoreFormat(orderData);

        new Thread(() -> {
            try {
                URL url = new URL(FIREBASE_URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json; utf-8");
                conn.setDoOutput(true);

                try (OutputStream os = conn.getOutputStream()) {
                    byte[] input = firestoreFormattedData.toString().getBytes("utf-8");
                    os.write(input, 0, input.length);
                }

                int responseCode = conn.getResponseCode();

                if (responseCode == 200 || responseCode == 201) {
                    javafx.application.Platform.runLater(() -> {
                        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
                        alert.setTitle("Order Confirmation");
                        alert.setHeaderText(null);
                        alert.setContentText("Your order has been placed successfully!");
                        alert.showAndWait();
                    });
                } else {
                    InputStream errorStream = conn.getErrorStream();
                    if (errorStream != null) {
                        BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));
                        String line;
                        while ((line = errorReader.readLine()) != null) {
                            System.out.println(line);
                        }
                        errorReader.close();
                    }

                    javafx.application.Platform.runLater(() -> {
                        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                        alert.setTitle("Order Failed");
                        alert.setHeaderText(null);
                        alert.setContentText("Failed to place order. Please try again.");
                        alert.showAndWait();
                    });
                }

                conn.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
                javafx.application.Platform.runLater(() -> {
                    javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                    alert.setTitle("Order Error");
                    alert.setHeaderText(null);
                    alert.setContentText("An error occurred while placing your order.");
                    alert.showAndWait();
                });
            }
        }).start();
    }

    private JSONObject convertToFirestoreFormat(JSONObject data) {
        JSONObject firestoreData = new JSONObject();
        JSONObject fields = new JSONObject();

        for (String key : data.keySet()) {
            Object value = data.get(key);
            JSONObject field = new JSONObject();

            if (value instanceof String) {
                field.put("stringValue", value);
            } else if (value instanceof Integer) {
                field.put("integerValue", value.toString());
            } else if (value instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) value;
                JSONArray firestoreArray = new JSONArray();

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject item = jsonArray.getJSONObject(i);
                    JSONObject mapValue = new JSONObject();
                    JSONObject itemFields = new JSONObject();

                    for (String itemKey : item.keySet()) {
                        Object itemVal = item.get(itemKey);
                        JSONObject innerField = new JSONObject();

                        if (itemVal instanceof String) {
                            innerField.put("stringValue", itemVal);
                        } else if (itemVal instanceof Integer) {
                            innerField.put("integerValue", itemVal.toString());
                        }

                        itemFields.put(itemKey, innerField);
                    }

                    mapValue.put("fields", itemFields);
                    firestoreArray.put(new JSONObject().put("mapValue", mapValue));
                }

                field.put("arrayValue", new JSONObject().put("values", firestoreArray));
            }

            fields.put(key, field);
        }

        firestoreData.put("fields", fields);
        return firestoreData;
    }
}



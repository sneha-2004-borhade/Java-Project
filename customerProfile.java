package com.home;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class customerProfile {

    public VBox getView (Stage stage,Runnable onBack) {
        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #fdf6ff;");

        Button backBtn = new Button("← Back");
        backBtn.setStyle("-fx-background-color: #DA70D6; -fx-text-fill: white; -fx-font-size: 16px;");
        backBtn.setOnMouseEntered(e -> backBtn.setStyle("-fx-background-color:#8743abff; -fx-text-fill: white; -fx-cursor: hand;"));
        backBtn.setOnMouseExited(e -> backBtn.setStyle("-fx-background-color: #DA70D6; -fx-text-fill: white; -fx-cursor: hand;"));
        backBtn.setOnAction(e -> onBack.run());
        

        Button logoutBtn = new Button("Log Out");
        logoutBtn.setStyle("-fx-background-color: #e3c6f5; -fx-text-fill: white;-fx-font-size: 14px;-fx-background-radius: 20;");
        //logoutBtn.setStyle("-fx-background-color: #8B0000; -fx-text-fill: white; -fx-font-size: 16px;");
        logoutBtn.setOnMouseEntered(e -> logoutBtn.setStyle("-fx-background-color: #e3c6f5; -fx-text-fill: white; -fx-cursor: hand;"));
        logoutBtn.setOnMouseExited(e -> logoutBtn.setStyle("-fx-background-color: #e3c6f5; -fx-text-fill: white; -fx-cursor: hand;"));
        //root.getChildren().addAll(backBtn,logoutBtn);

        // logoutBtn.setOnAction(e -> {
        //     Select_role selectPage = new Select_role();
        //     selectPage.setStage(stage);
        //     VBox sceneRoot = selectPage.createScene(() -> {});
        //     Scene selectScene = new Scene(sceneRoot, 1500, 800);
        //     stage.setScene(selectScene);
        // });

        logoutBtn.setOnAction(e -> {
    Select_role selectPage = new Select_role();
    selectPage.setStage(stage);  // now 'stage' is passed
    VBox sceneRoot = selectPage.createScene(() -> {});
    Scene selectScene = new Scene(sceneRoot, 1500, 800);
    stage.setScene(selectScene);  // now this works
});


        Label title = new Label("Customer Profile");
        title.setFont(Font.font("Georgia", FontWeight.BOLD, 28));
        title.setTextFill(Color.web("#6C3B8E"));

        ImageView profileImage = new ImageView(new Image("https://cdn-icons-png.flaticon.com/512/847/847969.png"));
        profileImage.setFitHeight(150);
        profileImage.setFitWidth(150);
        profileImage.setClip(new Circle(75, 75, 75));

        Label nameLabel = new Label("Name: ");
        Label phoneLabel = new Label("Phone: ");
        Label addressLabel = new Label("Address: ");

        for (Label lbl : new Label[]{nameLabel, phoneLabel, addressLabel}) {
            lbl.setFont(Font.font("Arial", 16));
        }

        root.getChildren().addAll(title, profileImage, nameLabel, phoneLabel, addressLabel,backBtn,logoutBtn);

        new Thread(() -> {
            try {
                JSONObject data = fetchCustomerProfile();
                if (data != null) {
                    JSONObject fields = data.getJSONObject("fields");

                    String name = fields.getJSONObject("name").getString("stringValue");
                    String phone = fields.getJSONObject("phoneNumber").getString("stringValue");
                    String address = fields.getJSONObject("address").getString("stringValue");
                    String imageUrl = fields.getJSONObject("imageUrl").getString("stringValue");

                    javafx.application.Platform.runLater(() -> {
                        nameLabel.setText("Name: " + name);
                        phoneLabel.setText("Phone: " + phone);
                        addressLabel.setText("Address: " + address);
                        profileImage.setImage(new Image(imageUrl, true));
                    });
                } else {
                    System.out.println("No customer profile found.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        return root;

        
    }

    private JSONObject fetchCustomerProfile() throws Exception {
        String projectId = "glam-100a6";
        String collection = "ProfileData";

        URL url = new URL("https://firestore.googleapis.com/v1/projects/" + projectId + "/databases/(default)/documents/" + collection);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder responseStr = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseStr.append(line);
            }

            JSONObject json = new JSONObject(responseStr.toString());

            if (json.has("documents")) {
                JSONArray documents = json.getJSONArray("documents");
                if (documents.length() > 0) {
                    return documents.getJSONObject(0);
                }
            }
        } else {
            System.out.println("Error fetching profile. Code: " + conn.getResponseCode());
        }
        return null;

    }
}

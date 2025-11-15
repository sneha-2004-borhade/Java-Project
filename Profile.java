

package com.home;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;

public class Profile {

    Scene p3Scene;
    Stage stage;
    File selectedFile = null;

    public void setScene(Scene scene) {
        this.p3Scene = scene;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public VBox createScene(Runnable navigateNext) {

        Label title = new Label("Complete Your Profile");
        title.setFont(Font.font("Georgia", FontWeight.BOLD, 30));
        title.setStyle("-fx-text-fill: #6C3B8E;");

        Label subtitle = new Label("Don't worry, only you can see your personal data.\nNo one else will be able to see it.");
        subtitle.setWrapText(true);
        subtitle.setStyle("-fx-font-size: 15px; -fx-text-fill: #A275B6;");

        Image image = new Image("https://cdn-icons-png.flaticon.com/512/847/847969.png");
        ImageView profileImage = new ImageView(image);
        profileImage.setFitHeight(150);
        profileImage.setFitWidth(150);
        profileImage.setClip(new Circle(75, 75, 75));

        Button editIcon = new Button("✎");
        editIcon.setStyle("""
            -fx-background-color: #fdf6ff;
            -fx-text-fill: #4B2C5A;
            -fx-font-weight: bold;
            -fx-background-radius: 50%;
            -fx-border-color: #D8BFD8;
            -fx-border-radius: 50%;
            -fx-cursor: hand;
        """);
        editIcon.setPrefSize(30, 30);

        editIcon.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Profile Image");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
            );
            selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                Image selectedImage = new Image(selectedFile.toURI().toString());
                profileImage.setImage(selectedImage);
            }
        });

        VBox profileBox = new VBox(5, profileImage, editIcon);
        profileBox.setAlignment(Pos.CENTER);

        TextField nameField = createStyledTextField("Enter Your Name");
        TextField phoneField = createStyledTextField("Enter Phone number");
        TextField userField = createStyledTextField("Enter Your Address");

        

        ComboBox<String> genderBox = new ComboBox<>();
        genderBox.getItems().addAll("Male", "Female", "Other");
        genderBox.setPromptText("Select Gender");
        genderBox.setStyle("-fx-background-radius: 20; -fx-background-color: #f7ecfa; -fx-text-fill: #4B2C5A;");
        genderBox.setMaxWidth(300);
        genderBox.setPrefHeight(30);

        ComboBox<String> countryCode = new ComboBox<>();
        countryCode.getItems().addAll("+91", "+1", "+44");
        countryCode.setValue("+91");
        countryCode.setPrefHeight(30);
        countryCode.setMaxWidth(80);
        countryCode.setStyle("-fx-background-radius: 20; -fx-background-color: #f7ecfa; -fx-text-fill: #4B2C5A;");

        HBox phoneBox = new HBox(10, countryCode, phoneField);
        phoneBox.setAlignment(Pos.CENTER);

       Button completeBtn = new Button("Complete Profile");
        completeBtn.setStyle("""
            -fx-background-color: #C8A2C8;
            -fx-text-fill: white;
            -fx-font-weight: bold;
            -fx-font-size: 14px;
            -fx-background-radius: 20;
            -fx-cursor: hand;
        """);
        completeBtn.setPrefHeight(40);
        completeBtn.setMaxWidth(150);

//     completeBtn.setOnAction(event -> {
//     String name = nameField.getText();
//     String phone = phoneField.getText();
//     String address = userField.getText();
//     String gender = genderBox.getValue();
//     String fullPhone = countryCode.getValue() + phone;

//     if (name.isEmpty() || phone.isEmpty() || address.isEmpty() || gender == null || selectedFile == null) {
//         showAlert("Please fill all fields and select an image.");
//         return;
//     }
//         new Thread(() -> {
//         try {
//             String imageUrl = uploadImageToFirebase(selectedFile);
//             uploadDataToFirestore(name, fullPhone, address, imageUrl);

//             javafx.application.Platform.runLater(() -> {
//                 if ( navigateNext != null) {
//                     navigateNext.run(); 
//                 }
//             });

//         } catch (Exception e) {
//             e.printStackTrace();
//             javafx.application.Platform.runLater(() -> showAlert("Failed: " + e.getMessage()));
//         }
//     }).start(); 
// });

        completeBtn.setOnAction(event -> {
    String name = nameField.getText().trim();
    String phone = phoneField.getText().trim();
    String address = userField.getText().trim();
    String gender = genderBox.getValue();
    String fullPhone = countryCode.getValue() + phone;

    if (name.isEmpty()) {
        showAlert("Name is required.");
        return;
    }
    if (!name.matches("[a-zA-Z ]+")) {
        showAlert("Name must contain only alphabets and spaces.");
        return;
    }

    if (address.isEmpty()) {
        showAlert("Address is required.");
        return;
    }
    if (!address.matches("[a-zA-Z0-9 ,.-]+")) {
        showAlert("Address can only contain letters, numbers, commas, periods, and spaces.");
        return;
    }

    if (phone.isEmpty()) {
        showAlert("Phone number is required.");
        return;
    }
    if (!phone.matches("\\d{7,15}")) {
        showAlert("Phone number must be 7 to 15 digits.");
        return;
    }

    if (gender == null || gender.isEmpty()) {
        showAlert("Please select a gender.");
        return;
    }

    if (selectedFile == null) {
        showAlert("Please select a profile image.");
        return;
    }

    new Thread(() -> {
        try {
            String imageUrl = uploadImageToFirebase(selectedFile);
            uploadDataToFirestore(name, fullPhone, address, imageUrl);

            javafx.application.Platform.runLater(() -> {
                if (navigateNext != null) {
                    showAlert("✅ Profile completed successfully!");
                    navigateNext.run();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            javafx.application.Platform.runLater(() -> showAlert("❌ Failed: " + e.getMessage()));
        }
    }).start();
});


        VBox root = new VBox(15,
                title, subtitle, profileBox,
                new Label("Name"), nameField, 
                new Label("Address"),userField,
                new Label("Phone Number"), phoneBox,
                new Label("Gender"), genderBox,
                completeBtn
        );
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #fdf6ff;");

        return root;
    }

    private TextField createStyledTextField(String prompt) {
        TextField tf = new TextField();
        tf.setPromptText(prompt);
        tf.setMaxWidth(300);
        tf.setPrefHeight(30);
        tf.setStyle("-fx-background-radius: 20; -fx-background-color: #f7ecfa; -fx-text-fill: #4B2C5A;");
        return tf;
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private String uploadImageToFirebase(File file) throws Exception {
        String bucketName = "glam-100a6"; 
        String fileName = "profile_images/" + System.currentTimeMillis() + "_" + file.getName();
        String firebaseUrl = "https://firebasestorage.googleapis.com/v0/b/" + bucketName + "/o/" +
                fileName.replace("/", "%2F") + "?uploadType=media&name=" + fileName;

        HttpURLConnection conn = (HttpURLConnection) new URL(firebaseUrl).openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", Files.probeContentType(file.toPath()));

        try (OutputStream os = conn.getOutputStream(); FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1)
                os.write(buffer, 0, bytesRead);
        }

        if (conn.getResponseCode() == 200) {
            InputStream response = conn.getInputStream();
            String json = new String(response.readAllBytes());
            JSONObject obj = new JSONObject(json);

            String downloadToken = obj.getString("downloadTokens");

            String imageUrl = "https://firebasestorage.googleapis.com/v0/b/" + bucketName + "/o/" +
            fileName.replace("/", "%2F") + "?alt=media&token=" + downloadToken;

            return imageUrl;
        } else {
            throw new IOException("Upload failed: " + conn.getResponseCode());
        }
    }
    
private void uploadDataToFirestore(String name, String phoneNumber, String address, String imageUrl) {
    try {
        String projectId = "glam-100a6";
        String collection = "ProfileData"; 

        URL url = new URL("https://firestore.googleapis.com/v1/projects/" + projectId + "/databases/(default)/documents/" + collection);

        JSONObject json = new JSONObject();
        JSONObject fields = new JSONObject();

        fields.put("name", new JSONObject().put("stringValue", name));
        fields.put("phoneNumber", new JSONObject().put("stringValue", phoneNumber));
        fields.put("address", new JSONObject().put("stringValue", address));
        fields.put("imageUrl", new JSONObject().put("stringValue", imageUrl));

        json.put("fields", fields);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

        OutputStream os = connection.getOutputStream();
        os.write(json.toString().getBytes("UTF-8"));
        os.close();

        int responseCode = connection.getResponseCode();
        System.out.println("Response code: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
            System.out.println("✅ Profile data uploaded to Firestore.");
        } else {
            System.out.println("❌ Firestore upload failed.");
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);  
            }
            br.close();
        }

        connection.disconnect();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

}





// package com.home;

// import java.io.InputStream;
// import java.io.OutputStream;
// import java.net.HttpURLConnection;
// import java.net.URL;
// import java.util.Scanner;

// import org.json.JSONArray;
// import org.json.JSONObject;

// import javafx.application.Application;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.TextArea;
// import javafx.scene.layout.VBox;
// import javafx.scene.text.Text;
// import javafx.stage.Stage;

// public class AIsuggestionPage {

    
//     private static final String API_KEY = "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"; // ← इथे तुझी key टाक
//     private static final String API_URL = "https://openrouter.ai/api/v1/chat/completions";

//     private Stage stage;

//     public AIsuggestionPage(Stage stage) {
//         this.stage = stage;
//     }
//         VBox root = new VBox(15);
//         root.setPadding(new Insets(20));
//         root.setAlignment(Pos.CENTER);

//         Text title = new Text("💬 GlamBot - Your Style Assistant");

//         TextArea inputArea = new TextArea();
//         inputArea.setPromptText("Ask something like: red lehenga");
//         inputArea.setPrefHeight(100);

//         Button sendBtn = new Button("Ask Bot");
//         TextArea responseArea = new TextArea();
//         responseArea.setEditable(false);
//         responseArea.setPrefHeight(200);
//         responseArea.setWrapText(true); // ✅ wrap text to avoid horizontal scroll

//         // responseArea.setEditable(false);
//         // responseArea.setPrefHeight(200);

//         sendBtn.setOnAction(e -> {
//             String userInput = inputArea.getText().trim();
//             if (!userInput.isEmpty()) {
//                 String response = getBotReply(userInput);
//                 responseArea.setText(response);
//             }
//         });

//         root.getChildren().addAll(title, inputArea, sendBtn, responseArea);

//         Scene scene = new Scene(root, 600, 500);
//         primaryStage.setScene(scene);
//         primaryStage.setTitle("GlamBot - Chat Assistant");
//         primaryStage.show();
//     }

//     private String getBotReply(String prompt) {
//     try {
//         URL url = new URL(API_URL);
//         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//         conn.setRequestMethod("POST");
//         conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
//         conn.setRequestProperty("Content-Type", "application/json");
//         conn.setDoOutput(true);

//         String jsonInput = """
// {
//   "model": "mistralai/mistral-7b-instruct",
//   "messages": [{
//     "role": "user",
//     "content": "Suggest a specific outfit idea for '%s' in 20 to 30 words only. Keep it short and practical. Do not explain."
//   }]
// }
// """.formatted(prompt);

//         try (OutputStream os = conn.getOutputStream()) {
//             byte[] input = jsonInput.getBytes("utf-8");
//             os.write(input, 0, input.length);
//         }

//         InputStream in = conn.getInputStream();
//         Scanner scanner = new Scanner(in).useDelimiter("\\A");
//         String response = scanner.hasNext() ? scanner.next() : "";
//         scanner.close();

//         // ✅ JSON Parsing
//         JSONObject obj = new JSONObject(response);
//         JSONArray choices = obj.getJSONArray("choices");
//         JSONObject message = choices.getJSONObject(0).getJSONObject("message");
//         String reply = message.getString("content");

//         return reply.trim();
//     } catch (Exception e) {
//         return "Error: " + e.getMessage();
//     }
// }




package com.home;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AIsuggestionPage {

    private static final String API_KEY = "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"; 
    private static final String API_URL = "https://openrouter.ai/api/v1/chat/completions";

    private Stage stage;

    public AIsuggestionPage(Stage stage) {
        this.stage = stage;
    }

    public VBox getView(Runnable onBack) {
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #fff0f5");

        Text title = new Text("💬 GlamBot - Your Style Assistant");
        title.setStyle("-fx-background-color: #C8A2C8;");
        title.setFont(Font.font("Arial", 22));

        TextArea inputArea = new TextArea();
        inputArea.setPromptText("Ask something like: red lehenga");
        inputArea.setPrefHeight(100);
        inputArea.setPrefWidth(100);
        inputArea.setWrapText(true);

        Button sendBtn = new Button("Ask Bot");

        TextArea responseArea = new TextArea();
        responseArea.setEditable(false);
        responseArea.setPrefHeight(200);
        responseArea.setPrefWidth(100);
        responseArea.setWrapText(true);

        sendBtn.setOnAction(e -> {
            String userInput = inputArea.getText().trim();
            if (!userInput.isEmpty()) {
                responseArea.setText("Thinking... please wait...");
                new Thread(() -> {
                    String response = getBotReply(userInput);
                    javafx.application.Platform.runLater(() -> responseArea.setText(response));
                }).start();
            }
        });

        // 👇 Back Button to return to CustomerPage
        Button backBtn = new Button("← Back");
        backBtn.setStyle("-fx-background-color: #C8A2C8; -fx-text-fill: white; -fx-font-weight: bold;");
        backBtn.setOnAction(e -> onBack.run());
        backBtn.setAlignment(Pos.CENTER_LEFT);

        root.getChildren().addAll(title, inputArea, sendBtn, responseArea, backBtn);
        return root;
    }

    private String getBotReply(String prompt) {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonInput = """
            {
              "model": "mistralai/mistral-7b-instruct",
              "messages": [{
                "role": "user",
                "content": "Suggest a specific outfit idea for '%s' in 20 to 30 words only. Keep it short and practical. Do not explain."
              }]
            }
            """.formatted(prompt);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInput.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            InputStream in = conn.getInputStream();
            Scanner scanner = new Scanner(in).useDelimiter("\\A");
            String response = scanner.hasNext() ? scanner.next() : "";
            scanner.close();

            JSONObject obj = new JSONObject(response);
            JSONArray choices = obj.getJSONArray("choices");
            JSONObject message = choices.getJSONObject(0).getJSONObject("message");
            String reply = message.getString("content");

            return reply.trim();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}

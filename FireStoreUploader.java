package com.home;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

 
public class FireStoreUploader {

    // 🔁 Replace with your actual Firebase project details
    private static final String PROJECT_ID = "glam-100a6";
    private static final String COLLECTION_NAME = "products";

    public static void saveProduct(String name, String price, String category, String description, String imageUrl) throws Exception {

        String jsonPayload = "{"
                + "\"fields\": {"
                + "\"name\": {\"stringValue\": \"" + name + "\"},"
                + "\"price\": {\"stringValue\": \"" + price + "\"},"
                + "\"category\": {\"stringValue\": \"" + category + "\"},"
                + "\"description\": {\"stringValue\": \"" + description + "\"},"
                + "\"imageUrl\": {\"stringValue\": \"" + imageUrl + "\"}"
                + "}"
                + "}";

        String urlStr = "https://firestore.googleapis.com/v1/projects/" + PROJECT_ID
                + "/databases/(default)/documents/" + COLLECTION_NAME;

        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonPayload.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = conn.getResponseCode();
        if (responseCode == 200 || responseCode == 201) {
            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNextLine()) {
                response.append(scanner.nextLine());
            }
            scanner.close();
            System.out.println("✅ Upload successful: " + response);
        } else {
            Scanner scanner = new Scanner(conn.getErrorStream());
            StringBuilder error = new StringBuilder();
            while (scanner.hasNextLine()) {
                error.append(scanner.nextLine());
            }
            scanner.close();
            throw new Exception("❌ Upload failed: HTTP " + responseCode + " → " + error.toString());
        }
    }

}
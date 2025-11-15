
package com.home;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;



public class ReportController {
    public static int totalBookings = 0;
    public static int completedBookings = 0;
    public static int pendingBookings = 0;
    public static int totalRevenue = 0;

    private static final String FIREBASE_URL = "https://firestore.googleapis.com/v1/projects/glam-100a6/databases/(default)/documents/reports/summary";

    public static void incrementPendingBooking() {
        pendingBookings++;
        totalBookings++;
        System.out.println("Pending booking incremented → Pending: " + pendingBookings + ", Total: " + totalBookings);
        updateSummaryInFirestore();
    }

    public static void markBookingAsCompleted(int amount) {
        completedBookings++;
        pendingBookings--;
        if (pendingBookings < 0) pendingBookings = 0;
        totalRevenue += amount;
        System.out.println("Booking marked completed. Total: " + totalBookings +
                ", Completed: " + completedBookings +
                ", Pending: " + pendingBookings +
                ", Revenue: ₹" + totalRevenue);
        updateSummaryInFirestore();
    }

    public static void fetchSummaryFromFirestore() {
        new Thread(() -> {
            try {
                URL url = new URL(FIREBASE_URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
                br.close();

                JSONObject json = new JSONObject(response.toString());
                JSONObject fields = json.getJSONObject("fields");

                totalBookings = Integer.parseInt(fields.getJSONObject("totalBookings").getString("integerValue"));
                completedBookings = Integer.parseInt(fields.getJSONObject("completedBookings").getString("integerValue"));
                pendingBookings = Integer.parseInt(fields.getJSONObject("pendingBookings").getString("integerValue"));
                totalRevenue = Integer.parseInt(fields.getJSONObject("totalRevenue").getString("integerValue"));

                System.out.println("Fetched summary → Total: " + totalBookings +
                        ", Completed: " + completedBookings +
                        ", Pending: " + pendingBookings +
                        ", Revenue: ₹" + totalRevenue);

            } catch (Exception e) {
                System.out.println("❌ Error fetching summary document.");
                e.printStackTrace();
            }
        }).start();
    }


    public static void updateSummaryInFirestore() {
    new Thread(() -> {
        try {
            JSONObject fields = new JSONObject();
            fields.put("totalBookings", new JSONObject().put("integerValue", String.valueOf(totalBookings)));
            fields.put("completedBookings", new JSONObject().put("integerValue", String.valueOf(completedBookings)));
            fields.put("pendingBookings", new JSONObject().put("integerValue", String.valueOf(pendingBookings)));
            fields.put("totalRevenue", new JSONObject().put("integerValue", String.valueOf(totalRevenue)));

            JSONObject data = new JSONObject();
            data.put("fields", fields);

            URL url = new URL("https://firestore.googleapis.com/v1/projects/glam-100a6/databases/(default)/documents/reports/summary");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            // ✅ Use POST method and override to PATCH
            conn.setRequestMethod("POST");
            conn.setRequestProperty("X-HTTP-Method-Override", "PATCH");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(data.toString().getBytes("utf-8"));
            }

            int responseCode = conn.getResponseCode();
            System.out.println("✅ Summary updated in Firestore → Response Code: " + responseCode);
            conn.disconnect();

        } catch (Exception e) {
            System.out.println("❌ Error updating summary in Firestore.");
            e.printStackTrace();
        }
    }).start();
}

    public ReportData fetchReportData() {
        ReportData data = new ReportData();
        data.totalBookings = totalBookings;
        data.completedBookings = completedBookings;
        data.pendingBookings = pendingBookings;
        data.totalRevenue = totalRevenue;
        return data;
    }

    public static class ReportData {
        public int totalBookings;
        public int completedBookings;
        public int pendingBookings;
        public int totalRevenue;
    }
}

package com.home;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiController {

    private static final String API_KEY = "AIzaSyADIfRTu1LcDCxyktQf_v3SKpprJ3Cel5k";

    public boolean authenticateUser(String email, String password, boolean isSignUp) {
        try {
            String endpoint = isSignUp
                    ? "https://identitytoolkit.googleapis.com/v1/accounts:signUp"
                    : "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword";

            URL url = new URL(endpoint + "?key=" + API_KEY);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String payload = String.format(
                    "{\"email\":\"%s\",\"password\":\"%s\",\"returnSecureToken\":true}",
                    email, password);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(payload.getBytes());
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                return true;
            } else {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream()))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                    }
                }
                return false;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean sendEmailVerification(String idToken) {
    try {
        URL url = new URL("https://identitytoolkit.googleapis.com/v1/accounts:sendOobCode?key=" + API_KEY);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        String payload = String.format("{\"requestType\":\"VERIFY_EMAIL\",\"idToken\":\"%s\"}", idToken);
        try (OutputStream os = conn.getOutputStream()) {
            os.write(payload.getBytes());
        }

        return conn.getResponseCode() == 200;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

}

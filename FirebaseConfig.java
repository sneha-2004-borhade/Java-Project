package com.home;

import java.io.FileInputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class FirebaseConfig {
    public static void initialize() {
        try {
         //   FileInputStream serviceAccount = new FileInputStream("src/main/resources/glam-100a6-firebase-adminsdk-fbsvc-3d0f25dd37.json");

         FileInputStream serviceAccount = new FileInputStream("C:\\Users\\sneha\\Downloads\\glam-100a6-firebase-adminsdk-fbsvc-3d0f25dd37.json");


            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setStorageBucket("glam-100a6.appspot.com")
                    .build();

            FirebaseApp.initializeApp(options);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}